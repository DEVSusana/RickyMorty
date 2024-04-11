package com.susanadev.rickymorty.data.repository

import com.susanadev.data.dataSource.RemoteDataSource
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.rickymorty.data.api.ApiService
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {
    override suspend fun getCharacterOfId(id: Int): Response<CharacterInfo> {
        return apiService.getCharacterOfId(id)
    }
}