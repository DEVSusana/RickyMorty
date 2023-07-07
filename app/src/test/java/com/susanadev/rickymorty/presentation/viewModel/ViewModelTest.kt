package com.susanadev.rickymorty.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.google.gson.Gson
import com.susanadev.rickymorty.data.api.ApiService
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.model.Location
import com.susanadev.rickymorty.data.model.Origin
import com.susanadev.rickymorty.data.repository.dataSource.RemoteDataSource
import com.susanadev.rickymorty.data.repository.dataSourceImpl.RemoteDataSourceImpl
import com.susanadev.rickymorty.data.utils.Resource
import com.susanadev.rickymorty.domain.repository.Repository
import com.susanadev.rickymorty.domain.usecase.GetDetailUseCase
import com.susanadev.rickymorty.view.pagin.ResultDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var getDetailUseCase: GetDetailUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ViewModel

    private lateinit var mockWebServer: MockWebServer

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = ViewModel(application, getDetailUseCase)
        viewModel.dispatcher = dispatcher
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
    }

    @Test
    fun `test isNetworkAvailable returns true`() {
        // Mock the connectivity manager and network capabilities
        val context = mock<Context>()
        val connectivityManager = mock(ConnectivityManager::class.java)
        `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE))
            .thenReturn(connectivityManager)
        val networkCapabilities = mock(NetworkCapabilities::class.java)
        `when`(connectivityManager.activeNetwork)
            .thenReturn(mock())
        `when`(connectivityManager.getNetworkCapabilities(any()))
            .thenReturn(networkCapabilities)

        // Set up the desired network capabilities for the test case
        `when`(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
            .thenReturn(true)
        `when`(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
            .thenReturn(true)
        `when`(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            .thenReturn(true)

        // Invoke the function being tested
        val result = viewModel.isNetworkAvailable(context)

        // Verify the result
        assertTrue(result)
    }

    @Test
    fun `test isNetworkAvailable returns false when context is null`() {
        val result = viewModel.isNetworkAvailable(null)

        assertEquals(false, result)
    }

    @Test
    fun `test invalidateResultDataSource`() {
        val mockResultDataSource = mock<ResultDataSource>()

        viewModel.resultDataSource = mockResultDataSource
        viewModel.invalidateResultDataSource()

        verify(mockResultDataSource).invalidate()
    }

    @Test
    fun `test setName`() {
        val name = "Rick"
        viewModel.setName(name)

        assertEquals(name, viewModel.name.value)
    }

    @Test
    fun `getCharacterDetailResponse should post Loading state and fetch data when network is available`() =
        runTest {
            // Mock network availability
            val context = mock<Context>()
            val connectivityManager = mock(ConnectivityManager::class.java)
            `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .thenReturn(connectivityManager)
            val networkCapabilities = mock(NetworkCapabilities::class.java)
            `when`(connectivityManager.activeNetwork)
                .thenReturn(mock())
            `when`(connectivityManager.getNetworkCapabilities(any()))
                .thenReturn(networkCapabilities)

            // Set up the desired network capabilities for the test case
            `when`(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
                .thenReturn(true)
            `when`(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
                .thenReturn(true)
            `when`(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                .thenReturn(true)

            // Set up response
            val characterInfo = CharacterInfo(
                created = "2023-07-03",
                episode = listOf("S01E01", "S01E02"),
                gender = "Male",
                id = 123,
                image = "https://example.com/image.jpg",
                location = Location("tierra", "https://example.com/character/123"),
                name = "Rick Sanchez",
                origin = Origin("tierra", "https://example.com/character/123"),
                species = "Human",
                status = "Alive",
                type = "Main Character",
                url = "https://example.com/character/123"
            )
            `when`(getDetailUseCase.execute(Mockito.anyInt())).thenReturn(
                Resource.Success(
                    characterInfo
                )
            )
            // Invoke the method
            viewModel.getCharacterDetailResponse(123, context)
            advanceUntilIdle()

            // Verify the expected behavior
            assertEquals(characterInfo, viewModel.getCharacterDetail.value?.data)
        }

    @Test
    fun `getCharacterDetailResponse should post Error state when network is not available`() =
        runTest {
            // Mock network availability
            val context = mock<Context>()
            val connectivityManager = mock(ConnectivityManager::class.java)
            `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .thenReturn(connectivityManager)
            val networkCapabilities = mock(NetworkCapabilities::class.java)
            `when`(connectivityManager.activeNetwork)
                .thenReturn(mock())
            `when`(connectivityManager.getNetworkCapabilities(any()))
                .thenReturn(networkCapabilities)

            // Set up the desired network capabilities for the test case
            `when`(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
                .thenReturn(false)

            // Invoke the method
            viewModel.getCharacterDetailResponse(123, context)
            advanceUntilIdle()

            assert(viewModel.getCharacterDetail.value is Resource.Error)
    }

    @Test
    fun `getCharacterDetailResponse should post Error state when return an exception`() =
        runTest {
            // Invoke the method
            viewModel.getCharacterDetailResponse(123)
            advanceUntilIdle()

            assert(viewModel.getCharacterDetail.value is Resource.Error)
        }


}
