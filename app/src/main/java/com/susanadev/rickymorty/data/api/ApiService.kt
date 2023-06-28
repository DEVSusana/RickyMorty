package com.susanadev.rickymorty.data.api

import com.susanadev.rickymorty.data.model.ApiResponse
import com.susanadev.rickymorty.data.model.CharacterInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacterList(
        @Query("page") page: Int
    ): Response<ApiResponse>

    @GET("character/{characterId}")
    suspend fun getCharacterOfId(
        @Path("characterId") characterId: Int
    ): Response<CharacterInfo>

    @GET("character")
    suspend fun getSearchCharacter(
        @Query("name") characterName: String,
        @Query("page") page: Int
    ): Response<ApiResponse>

}