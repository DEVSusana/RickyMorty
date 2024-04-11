package com.susanadev.usecases

import androidx.paging.Pager
import androidx.paging.PagingData
import com.susanadev.domain.model.CharacterInfo
import kotlinx.coroutines.flow.Flow

class GetFilteredListOfCharactersUseCase(private val repository: com.susanadev.domain.repository.Repository){
    suspend fun execute(pager: Pager<Int, com.susanadev.domain.model.CharacterInfo>): Flow<PagingData<CharacterInfo>> {
        return repository.getFilteredListOfCharacters(pager)
    }

}