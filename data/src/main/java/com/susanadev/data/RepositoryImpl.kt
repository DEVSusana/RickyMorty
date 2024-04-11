package com.susanadev.data

import androidx.paging.Pager
import androidx.paging.PagingData
import com.susanadev.data.dataSource.RemoteDataSource
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.repository.Repository
import com.susanadev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getCharacterOfId(id: Int): Resource<CharacterInfo> {
        return responseToResource(remoteDataSource.getCharacterOfId(id))
    }

    override suspend fun getListOfCharacter(pager: Pager<Int, CharacterInfo>): Flow<PagingData<CharacterInfo>> {
        return pager.flow
    }

    override suspend fun getFilteredListOfCharacters(pager: Pager<Int, CharacterInfo>): Flow<PagingData<CharacterInfo>> {
        return pager.flow
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