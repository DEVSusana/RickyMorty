package com.susanadev.rickymorty.domain.repository

import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.utils.Resource

interface Repository {

    suspend fun getCharacterOfId(id: Int): Resource<CharacterInfo>

}