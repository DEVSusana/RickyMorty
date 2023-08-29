package com.susanadev.rickymorty.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.utils.Resource
import com.susanadev.rickymorty.domain.usecase.GetDetailUseCase
import com.susanadev.rickymorty.view.pagin.ResultDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(
    private val app: Application,
    private val getDetailUseCase: GetDetailUseCase
) : AndroidViewModel(app) {

    var name = mutableStateOf("")
        private set

    lateinit var resultDataSource: ResultDataSource

    var dispatcher: CoroutineDispatcher = Dispatchers.IO

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities.isNetworkCapabilitiesValid()

    }

    private fun NetworkCapabilities?.isNetworkCapabilitiesValid(): Boolean = when {
        this == null -> false
        hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) -> true

        else -> false
    }

    fun invalidateResultDataSource() {
        resultDataSource.invalidate()
    }

    fun setName(name: String) {
        this.name.value = name
    }

    val resultCharacterList = Pager(PagingConfig(pageSize = 50)) {
        ResultDataSource("").also { resultDataSource = it }
    }.flow.cachedIn(viewModelScope)

    val resultSearchList = Pager(PagingConfig(pageSize = 50)) {
        ResultDataSource(name.value).also { resultDataSource = it }
    }.flow.cachedIn(viewModelScope)

    private val _getCharacterDetail: MutableLiveData<Resource<CharacterInfo>> by lazy {
        MutableLiveData<Resource<CharacterInfo>>()
    }

    val getCharacterDetail: LiveData<Resource<CharacterInfo>> get() = _getCharacterDetail

    fun getCharacterDetailResponse(id: Int, context: Context? = app) =
        viewModelScope.launch(dispatcher) {
            _getCharacterDetail.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(context)) {
                    val apiResult = getDetailUseCase.execute(id)
                    _getCharacterDetail.postValue(apiResult)
                } else {
                    _getCharacterDetail.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception) {
                _getCharacterDetail.postValue(Resource.Error(e.message.toString()))
            }
        }

}