package com.berteodosio.seriemesmo.presentation.showDetails.presenter

import com.berteodosio.seriemesmo.domain.model.Season
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.showDetails.view.ShowDetailsSeasonsView

class ShowDetailsSeasonsPresenter(private val view: ShowDetailsSeasonsView) : BasePresenter() {

    private var showId = 0L

    fun onInitialize(show: Show?) {
        if (show == null) {
            // TODO HANDLE ERROR
            return
        }

        this.showId = show.id
        view.initialize()
        view.displaySeasons(show.seasons)
    }

    fun onSeasonClick(season: Season) {
        view.navigateToSeasonDetailsScreen(season, showId = showId)
    }
}