package com.susanadev.rickymorty.view.pagin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.susanadev.domain.model.CharacterInfo
import com.susanadev.rickymorty.data.api.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ResultDataSource @Inject constructor(
    private val apiService: ApiService,
    val name: String
) : PagingSource<Int, CharacterInfo>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterInfo> {
        try {
            if (name.isNotEmpty()) {
                val outName = name.replace(" ", "_")
                val nextPage = 1
                val characterList = apiService
                    .getSearchCharacter(outName, nextPage)

                return LoadResult.Page(
                    data = characterList.body()?.results.orEmpty(),
                    prevKey = null,
                    nextKey = null
                )

            } else {
                val nextPage = params.key ?: 1
                val characterList = apiService
                    .getCharacterList(nextPage)

                return LoadResult.Page(
                    data = characterList.body()?.results.orEmpty(),
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = nextPage + 1
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }catch (exception: NullPointerException) {
            return LoadResult.Error(exception)
        }
    }

}