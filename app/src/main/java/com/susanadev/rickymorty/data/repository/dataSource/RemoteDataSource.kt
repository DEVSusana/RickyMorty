package com.susanadev.rickymorty.data.repository.dataSource

import com.susanadev.rickymorty.data.model.CharacterInfo
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getCharacterOfId(id: Int): Response<CharacterInfo>
}