package com.berteodosio.seriemesmo.domain.useCase.mapper

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbGenre
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import com.berteodosio.seriemesmo.domain.useCase.model.Show


// TODO: refactor
fun TmdbShow.toShow(): Show {
    val baseImageUrl = "https://www.themoviedb.org/t/p/w1280"
    return Show(
        id = this.id ?: 0L,
        name = this.name ?: "",
        overview = this.overview ?: "",
        voteAverage = this.voteAverage ?: 0.0,
        posterUrl = "$baseImageUrl${this.posterPath}",
        backdropUrl = "$baseImageUrl${this.backdropPath}",
        genres = mapGenres(this.genres),
        status = this.status ?: "",
        originalLanguage = this.originalLanguage ?: "",
        seasons = this.seasons?.map { it.toSeason() } ?: emptyList()
    )
}

private fun mapGenres(genres: List<TmdbGenre>?): List<String> {
    return genres
        ?.filter { it.name.isNullOrBlank().not() }
        ?.map { it.name ?: "" }
        ?: emptyList()
}
