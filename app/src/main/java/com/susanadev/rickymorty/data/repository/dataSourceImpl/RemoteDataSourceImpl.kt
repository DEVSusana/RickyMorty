package com.susanadev.rickymorty.data.repository.dataSourceImpl

import com.susanadev.rickymorty.data.api.ApiService
import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.repository.dataSource.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun getCharacterOfId(id: Int): Response<CharacterInfo> {
        return apiService.getCharacterOfId(id)
    }
}