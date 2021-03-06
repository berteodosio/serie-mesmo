package com.berteodosio.seriemesmo.domain.mapper

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbEpisode
import com.berteodosio.seriemesmo.domain.model.Episode


fun TmdbEpisode.toEpisode(): Episode {
    return Episode(
        number = this.number ?: 0L,
        name = this.name ?: "",
        overview = this.overview ?: "",
        airDate = this.airDate ?: "",
        episodeImageUrl = "${TmdbConfig.IMAGE_URL_PREFIX}${this.stillPath}"
    )
}
