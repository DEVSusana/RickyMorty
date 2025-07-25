package com.susanadev.usecases

import androidx.paging.Pager
import androidx.paging.PagingData
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetFilteredListOfCharactersUseCase(private val repository: Repository){
    suspend fun execute(pager: Pager<Int, CharacterInfo>): Flow<PagingData<CharacterInfo>> {
        return repository.getFilteredListOfCharacters(pager)
    }

}