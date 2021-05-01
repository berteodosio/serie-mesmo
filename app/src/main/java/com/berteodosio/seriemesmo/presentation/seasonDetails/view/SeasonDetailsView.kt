package com.berteodosio.seriemesmo.presentation.seasonDetails.view

import com.berteodosio.seriemesmo.domain.model.Season

interface SeasonDetailsView {

    fun showLoading()

    fun displaySeasonDetails(season: Season)

    fun initialize(seasonName: String)

    fun navigateToEpisodeDetailsScreen(episodeName: String, episodeOverview: String, episodeCoverUrl: String,
                                       episodeAirDate: String, episodeNumber: Long)

}