package com.berteodosio.seriemesmo.presentation.episodeDetails.view

interface EpisodeDetailsView {

    fun initialize()

    fun displayEpisodeDetails(
        name: String,
        overview: String,
        coverUrl: String,
        airDate: String,
        number: Long
    )

}