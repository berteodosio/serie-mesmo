package com.berteodosio.seriemesmo.presentation.seasonDetails.presenter

import com.berteodosio.seriemesmo.domain.model.Episode
import com.berteodosio.seriemesmo.domain.useCase.base.setupCommonSchedulers
import com.berteodosio.seriemesmo.domain.useCase.season.FetchSeasonDetailsUseCase
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import com.berteodosio.seriemesmo.presentation.seasonDetails.view.SeasonDetailsView

class SeasonDetailsPresenter(
    private val view: SeasonDetailsView,
    private val fetchSeasonDetailsUseCase: FetchSeasonDetailsUseCase
) : BasePresenter() {

    fun onInitialize(showId: Long, seasonNumber: Long, seasonName: String) {
        if (showId <= 0 || seasonNumber <= 0) {
            // TODO: validate error
            return
        }

        view.initialize(seasonName)
        fetchSeasonDetails(showId, seasonNumber)
    }

    private fun fetchSeasonDetails(showId: Long, seasonNumber: Long) {
        view.showLoading()
        val disposable = fetchSeasonDetailsUseCase
            .execute(showId, seasonNumber)
            .setupCommonSchedulers()
            .subscribe(
                { season -> view.displaySeasonDetails(season)},
                { e ->
                    AppLogger.e(
                        TAG,
                        "Error loading season details",
                        e
                    )
                })      // TODO properly handle error

        addDisposable(disposable)
    }

    fun onEpisodeClick(episode: Episode) {
        view.navigateToEpisodeDetailsScreen(episodeName = episode.name, episodeOverview = episode.overview,
            episodeCoverUrl = episode.episodeImageUrl, episodeAirDate = episode.airDate, episodeNumber = episode.number)
    }
}