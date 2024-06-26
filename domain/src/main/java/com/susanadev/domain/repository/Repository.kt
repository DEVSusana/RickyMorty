package com.susanadev.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharacterOfId(id: Int): Resource<CharacterInfo>

    suspend fun getListOfCharacter(pager: Pager<Int, CharacterInfo>): Flow<PagingData<CharacterInfo>>

    suspend fun getFilteredListOfCharacters(pager: Pager<Int, CharacterInfo>): Flow<PagingData<CharacterInfo>>

}