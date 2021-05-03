package com.berteodosio.seriemesmo.domain.mapper

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbSeason
import com.berteodosio.seriemesmo.domain.model.Season

fun TmdbSeason.toSeason(): Season {
    return Season(
        name = this.name ?: "",
        posterUrl = "${TmdbConfig.IMAGE_URL_PREFIX}${this.posterPath}",
        episodeCount = this.episodeCount ?: 0L,
        episodes = this.episodes?.map { it.toEpisode() } ?: emptyList(),
        number = this.seasonNumber ?: 0L
    )
}
