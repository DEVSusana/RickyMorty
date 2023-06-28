package com.susanadev.rickymorty.data.repository

import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.repository.dataSource.RemoteDataSource
import com.susanadev.rickymorty.data.utils.Resource
import com.susanadev.rickymorty.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getCharacterOfId(id: Int): Resource<CharacterInfo> {
        return responseToResource(remoteDataSource.getCharacterOfId(id))
    }

    private fun responseToResource(response: Response<CharacterInfo>): Resource<CharacterInfo> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}