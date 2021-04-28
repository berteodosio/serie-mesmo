package com.berteodosio.seriemesmo.domain.usecase.model

data class Show(
    val id: Long,
    val name: String,
    val overview: String,
    val posterUrl: String,
    val voteAverage: Double
)