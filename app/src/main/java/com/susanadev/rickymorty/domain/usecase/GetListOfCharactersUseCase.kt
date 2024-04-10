package com.susanadev.rickymorty.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetListOfCharactersUseCase(private val repository: Repository){
    suspend fun execute(pager: Pager<Int, CharacterInfo>): Flow<PagingData<CharacterInfo>>  {
        return repository.getListOfCharacter(pager)
    }

}