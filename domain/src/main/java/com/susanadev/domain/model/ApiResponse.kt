package com.susanadev.domain.model

data class ApiResponse(
    val info: Info,
    val results: List<CharacterInfo>
)