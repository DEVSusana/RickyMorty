package com.susanadev.rickymorty.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharacterOfId(id: Int): Resource<CharacterInfo>

    suspend fun getListOfCharacter(pager: Pager<Int, CharacterInfo>): Flow<PagingData<CharacterInfo>>

    suspend fun getFilteredListOfCharacters(rpager: Pager<Int, CharacterInfo>): Flow<PagingData<CharacterInfo>>

}