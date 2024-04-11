package com.susanadev.rickymorty.data.repository.dataSourceImpl

import com.google.gson.Gson
import com.susanadev.rickymorty.data.api.ApiService
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.model.Location
import com.susanadev.domain.model.Origin
import com.susanadev.rickymorty.data.repository.RemoteDataSourceImpl
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        remoteDataSourceImpl = RemoteDataSourceImpl(apiService)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetCharacterOfId() {
        // Set up the mock response
        val characterInfo  = com.susanadev.domain.model.CharacterInfo(
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

        val mockResponse = MockResponse().setResponseCode(200).setBody(convertToJson(characterInfo))
        mockWebServer.enqueue(mockResponse)

        // Call the method under test
        val id = 123 // Set up your desired ID
        runBlocking {
            val response = remoteDataSourceImpl.getCharacterOfId(id)

            // Verify the request
            val recordedRequest = mockWebServer.takeRequest()
            assertEquals(recordedRequest.path, "/character/$id")

            // Verify the response
            assertTrue(response.isSuccessful)
            assertEquals(response.body(), characterInfo)
        }
    }

    @Test
    fun testGetCharacterOfIdWithException() {
        // Set up the mock response with error
        val mockResponse = MockResponse().setResponseCode(404).setBody("{}")
        mockWebServer.enqueue(mockResponse)

        // Call the method under test
        val id = 123 // Set up your desired ID
        runBlocking {
            val response = remoteDataSourceImpl.getCharacterOfId(id)

            // Verify the request
            val recordedRequest = mockWebServer.takeRequest()
            assertEquals(recordedRequest.path, "/character/$id")

            // Verify the response
            assertFalse(response.isSuccessful)
            assertEquals(response.code(), 404)
        }
    }


    private fun convertToJson(obj: Any): String {
        val gson = Gson()
        return gson.toJson(obj)
    }

}