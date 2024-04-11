package com.susanadev.rickymorty.data.repository

import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.model.Location
import com.susanadev.domain.model.Origin
import com.susanadev.data.dataSource.RemoteDataSource
import com.susanadev.domain.utils.Resource
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class RepositoryImplTest {
    private lateinit var mockWebServer: MockWebServer

    @Mock
    private lateinit var mockRemoteDataSource: com.susanadev.data.dataSource.RemoteDataSource

    private lateinit var repositoryImpl: com.susanadev.data.RepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        repositoryImpl = com.susanadev.data.RepositoryImpl(mockRemoteDataSource)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetCharacterOfIdSuccess() {
        // Set up the mock response
        val characterInfo = com.susanadev.domain.model.CharacterInfo(
            created = "2023-07-03",
            episode = listOf("S01E01", "S01E02"),
            gender = "Male",
            id = 123,
            image = "https://example.com/image.jpg",
            location = com.susanadev.domain.model.Location(
                "tierra",
                "https://example.com/character/123"
            ),
            name = "Rick Sanchez",
            origin = com.susanadev.domain.model.Origin(
                "tierra",
                "https://example.com/character/123"
            ),
            species = "Human",
            status = "Alive",
            type = "Main Character",
            url = "https://example.com/character/123"
        )

        // Mock the behavior of RemoteDataSource
        `when`(runBlocking { mockRemoteDataSource.getCharacterOfId(123) }).thenReturn(
            Response.success(
                characterInfo
            )
        )

        // Call the method under test
        val result = runBlocking { repositoryImpl.getCharacterOfId(123) }

        // Verify the result
        assertTrue(result is Resource.Success)
        assertEquals(characterInfo, (result as Resource.Success).data)
    }

    @Test
    fun testGetCharacterOfIdError() {
        // Set up the mock error response
        val errorMessage = "Not Found"
        val errorResponseBody = errorMessage.toResponseBody("text/plain".toMediaTypeOrNull())


        // Enqueue the error response using MockWebServer
        val mockResponse = MockResponse().setResponseCode(404).setBody(errorMessage)
        mockWebServer.enqueue(mockResponse)

        // Mock the behavior of RemoteDataSource
        `when`(runBlocking { mockRemoteDataSource.getCharacterOfId(123) }).thenReturn(
            Response.error(
                404,
                errorResponseBody
            )
        )

        // Call the method under test
        val result = runBlocking { repositoryImpl.getCharacterOfId(123) }
        result.message = errorMessage

        // Verify the result
        assertTrue(result is Resource.Error)
        assertEquals(errorMessage, (result as Resource.Error).message)
    }

}