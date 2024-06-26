package com.susanadev.rickymorty.view.pagin

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import com.susanadev.domain.model.ApiResponse
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.model.Info
import com.susanadev.domain.model.Location
import com.susanadev.domain.model.Origin
import com.susanadev.rickymorty.data.api.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

@ExperimentalCoroutinesApi
class ResultDataSourceTest {

    @Before
    fun setup() {

    }

    @After
    fun tearDown() {

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
                    location = Location(
                        "tierra",
                        "https://example.com/character/123"
                    ),
                    name = "Rick Sanchez",
                    origin = Origin(
                        "tierra",
                        "https://example.com/character/123"
                    ),
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
        val dataSource = ResultDataSource(apiService, "Rick")

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
                    location = Location(
                        "tierra",
                        "https://example.com/character/123"
                    ),
                    name = "Rick Sanchez",
                    origin = Origin(
                        "tierra",
                        "https://example.com/character/123"
                    ),
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
        val dataSource = ResultDataSource(apiService, "")

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
    @Test
    fun `load - exception occurs, returns LoadResult Error`() = runBlocking {
        // Mock API response
        val apiService = mock(ApiService::class.java)
        // Create the data source
        val dataSource = ResultDataSource(apiService, "")

        // Create load parameters
        val loadParams = LoadParams.Refresh(0, 1, false)

        // Call the load function
        val result = dataSource.load(loadParams)

        // Verify the expected result
        assert(result is LoadResult.Error)
        val errorResult = result as LoadResult.Error
        assert(errorResult.throwable is NullPointerException)
    }


}
