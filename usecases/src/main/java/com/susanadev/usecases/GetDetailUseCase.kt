package com.susanadev.usecases

import com.susanadev.domain.model.CharacterInfo
import com.susanadev.domain.repository.Repository
import com.susanadev.domain.utils.Resource


class GetDetailUseCase(private val repository: Repository) {
    suspend fun execute(id: Int): Resource<CharacterInfo> {
        return repository.getCharacterOfId(id)
    }

}