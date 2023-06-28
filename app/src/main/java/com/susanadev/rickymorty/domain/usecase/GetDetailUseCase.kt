package com.susanadev.rickymorty.domain.usecase

import com.susanadev.rickymorty.data.model.CharacterInfo
import com.susanadev.rickymorty.data.utils.Resource
import com.susanadev.rickymorty.domain.repository.Repository

class GetDetailUseCase (private val repository: Repository){

    suspend fun execute(id: Int): Resource<CharacterInfo>{
        return repository.getCharacterOfId(id)
    }

}