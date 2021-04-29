package com.berteodosio.seriemesmo.domain.useCase.mapper

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import com.berteodosio.seriemesmo.domain.useCase.model.Show


// TODO: refactor
fun TmdbShow.toShow(): Show {
    val baseImageUrl = "https://www.themoviedb.org/t/p/w1280"
    return Show(id = this.id, name = this.name, overview = this.overview, voteAverage = this.voteAverage, posterUrl = "$baseImageUrl${this.posterPath}", backdropUrl = "$baseImageUrl${this.backdropPath}")
}