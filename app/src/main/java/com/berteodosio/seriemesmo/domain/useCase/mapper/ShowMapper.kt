package com.berteodosio.seriemesmo.domain.useCase.mapper

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbGenre
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import com.berteodosio.seriemesmo.domain.useCase.model.Show


// TODO: refactor
fun TmdbShow.toShow(): Show {
    val baseImageUrl = "https://www.themoviedb.org/t/p/w1280"
    return Show(
        id = this.id,
        name = this.name,
        overview = this.overview,
        voteAverage = this.voteAverage,
        posterUrl = "$baseImageUrl${this.posterPath}",
        backdropUrl = "$baseImageUrl${this.backdropPath}",
        genres = mapGenres(this.genres)
    )
}

private fun mapGenres(genres: List<TmdbGenre>?): List<String> {
    return genres
        ?.filter { it.name.isNullOrBlank().not() }
        ?.map { it.name ?: "" }
        ?: emptyList()
}
