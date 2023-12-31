package com.susanadev.rickymorty.view.pagin

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import com.google.gson.Gson
import com.susanadev.rickymorty.data.api.ApiService
import com.susanadev.rickymorty.data.model.ApiResponse
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.model.Info
import com.susanadev.rickymorty.data.model.Location
import com.susanadev.rickymorty.data.model.Origin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class ResultDataSourceTest {
    private lateinit var mockWebServer: MockWebServer


    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun `load - with name, returns character list`() = runBlocking {
        // Mock API response
        val responseBody = ApiResponse(
            info = Info(0, "", 0, 0), results = listOf(
                CharacterInfo(
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
            )
        )
        val response = Response.success(responseBody)
        val apiService = mock(ApiService::class.java)
        `when`(apiService.getSearchCharacter(anyString(), anyInt())).thenReturn(response)

        // Create the data source
        val dataSource = ResultDataSource("Rick", apiService)

        // Create load parameters
        val loadParams = LoadParams.Refresh(0, 1, false)

        // Call the load function
        val result = dataSource.load(loadParams)

        // Verify the expected result
        assert(result is LoadResult.Page)
        val pageResult = result as LoadResult.Page
        assert(pageResult.data == responseBody.results)
        assert(pageResult.prevKey == null)
        assert(pageResult.nextKey == null)
    }

    @Test
    fun `load - without name, returns character list`() = runBlocking {
        // Mock API response
        val responseBody = ApiResponse(
            info = Info(0, "", 0, 0), results = listOf(
                CharacterInfo(
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
            )
        )
        val response = Response.success(responseBody)
        val apiService = mock(ApiService::class.java)
        `when`(apiService.getCharacterList(anyInt())).thenReturn(response)

        // Create the data source
        val dataSource = ResultDataSource("", apiService)

        // Create load parameters
        val loadParams = LoadParams.Refresh(1, 1, false)

        // Call the load function
        val result = dataSource.load(loadParams)

        // Verify the expected result
        assert(result is LoadResult.Page)
        val pageResult = result as LoadResult.Page
        assert(pageResult.data == responseBody.results)
        assert(pageResult.prevKey == null)
        assert(pageResult.nextKey == 2)
    }

    @Ignore("problems to mock exception")
    @Test
    fun `load - IO exception occurs, returns LoadResult Error`() = runBlocking {
        // Mock API response
        val errorMessage = "Not Found"
        val errorResponseBody = errorMessage.toResponseBody("text/plain".toMediaTypeOrNull())

        val apiService = mock(ApiService::class.java)
        `when`(apiService.getCharacterList(anyInt())).thenReturn(
            Response.error(
                500,
                errorResponseBody
            )
        )

        // Create the data source
        val dataSource = ResultDataSource("", apiService)

        // Create load parameters
        val loadParams = LoadParams.Refresh(1, 1, false)

        // Call the load function
        val result = dataSource.load(loadParams)
        val expectedResult = LoadResult.Error<Int, CharacterInfo>(IOException())

        // Verify the expected result
        assertEquals(result.toString(), expectedResult.toString())
        val errorResult = result as LoadResult.Error
        assert(errorResult.throwable is IOException)
    }

    @Ignore("problems to mock exception")
    @Test
    fun `load - HTTP exception occurs, returns LoadResult Error`() = runBlocking {
        // Mock API response
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        // Create the data source
        val dataSource = ResultDataSource("")

        // Create load parameters
        val loadParams = LoadParams.Refresh(0, 1, false)

        // Call the load function
        val result = dataSource.load(loadParams)

        // Verify the expected result
        assert(result is LoadResult.Error)
        val errorResult = result as LoadResult.Error
        assert(errorResult.throwable is HttpException)
    }


    private fun convertToJson(obj: Any): String {
        val gson = Gson()
        return gson.toJson(obj)
    }


}
