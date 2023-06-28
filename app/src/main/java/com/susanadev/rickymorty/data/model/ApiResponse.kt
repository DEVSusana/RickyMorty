package com.susanadev.rickymorty.data.model

data class ApiResponse(
    val info: Info,
    val results: List<CharacterInfo>
)