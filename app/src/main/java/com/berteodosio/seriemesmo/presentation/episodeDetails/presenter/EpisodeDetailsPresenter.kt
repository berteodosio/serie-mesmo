package com.berteodosio.seriemesmo.presentation.episodeDetails.presenter

import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.episodeDetails.view.EpisodeDetailsView

class EpisodeDetailsPresenter(private val view: EpisodeDetailsView) : BasePresenter() {
    fun onInitialize(
        episodeName: String,
        episodeOverview: String,
        episodeCoverUrl: String,
        episodeAirDate: String,
        episodeNumber: Long
    ) {
        view.initialize()
        view.displayEpisodeDetails(name = episodeName, overview = episodeOverview, coverUrl = episodeCoverUrl, airDate = episodeAirDate, number = episodeNumber)
    }
}