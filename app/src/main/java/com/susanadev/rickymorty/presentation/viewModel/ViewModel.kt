package com.susanadev.rickymorty.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.utils.Resource
import com.susanadev.rickymorty.data.api.ApiService
import com.susanadev.rickymorty.view.pagin.ResultDataSource
import com.susanadev.usecases.GetDetailUseCase
import com.susanadev.usecases.GetFilteredListOfCharactersUseCase
import com.susanadev.usecases.GetListOfCharactersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ViewModel(
    private val app: Application,
    private val getDetailUseCase: GetDetailUseCase,
    private val getListOfCharactersUseCase: GetListOfCharactersUseCase,
    private val getFilteredListOfCharactersUseCase: GetFilteredListOfCharactersUseCase,
    private val apiService: ApiService
) : AndroidViewModel(app) {

    var name = mutableStateOf("")
        private set

    private val _searchText = mutableStateOf(TextFieldValue(""))
    val searchText: State<TextFieldValue> = _searchText


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

    fun setSearchText(value: TextFieldValue) {
        _searchText.value = value
    }

    val resultCharacterList: Flow<PagingData<CharacterInfo>> = flow {
        val charactersList = getListOfCharactersUseCase.execute(
            Pager(PagingConfig(pageSize = 50)) {
                ResultDataSource(apiService, "").also { resultDataSource = it }
            }
        )
        emitAll(charactersList)
    }.cachedIn(viewModelScope)

    val resultSearchList: Flow<PagingData<CharacterInfo>> = flow {
        val filteredCharacters = getFilteredListOfCharactersUseCase.execute(
            Pager(PagingConfig(pageSize = 50)) {
                ResultDataSource(apiService, name.value).also { resultDataSource = it }
            }
        )
        emitAll(filteredCharacters)
    }.cachedIn(viewModelScope)

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