package com.susanadev.data.dataSource

import com.susanadev.domain.model.CharacterInfo
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getCharacterOfId(id: Int): Response<CharacterInfo>
}