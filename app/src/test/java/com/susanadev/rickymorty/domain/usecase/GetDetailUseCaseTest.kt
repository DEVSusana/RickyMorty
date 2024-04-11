package com.susanadev.rickymorty.domain.usecase

import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.model.Location
import com.susanadev.domain.model.Origin
import com.susanadev.domain.utils.Resource
import com.susanadev.domain.repository.Repository
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDetailUseCaseTest {

    @Mock
    private lateinit var mockRepository: com.susanadev.domain.repository.Repository

    private lateinit var getDetailUseCase: com.susanadev.usecases.usecase.GetDetailUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getDetailUseCase = com.susanadev.usecases.usecase.GetDetailUseCase(mockRepository)
    }

    @Test
    fun testExecuteSuccess() {
        // Set up the test data
        val id = 123
        val characterInfo = com.susanadev.domain.model.CharacterInfo(
            created = "2023-07-03",
            episode = listOf("S01E01", "S01E02"),
            gender = "Male",
            id = id,
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
        val expectedResource = Resource.Success(characterInfo)

        // Mock the behavior of the repository
        `when`(runBlocking { mockRepository.getCharacterOfId(id) }).thenReturn(expectedResource)

        // Call the method under test
        val result = runBlocking { getDetailUseCase.execute(id) }

        // Verify the result
        assertEquals(expectedResource, result)
        runBlocking {
            verify(mockRepository).getCharacterOfId(id)
        }
    }

    @Test
    fun testExecuteError() {
        // Set up the test data
        val id = 123
        val errorMessage = "Error retrieving character info"
        val expectedResource = Resource.Error<com.susanadev.domain.model.CharacterInfo>(errorMessage)

        // Mock the behavior of the repository
        runBlocking {
            `when`(mockRepository.getCharacterOfId(id)).thenReturn(expectedResource as Resource<CharacterInfo>?)
        }

        // Call the method under test
        val result = runBlocking { getDetailUseCase.execute(id) }

        // Verify the result
        assertEquals(expectedResource, result)
        runBlocking {
            verify(mockRepository).getCharacterOfId(id)
        }
    }


}