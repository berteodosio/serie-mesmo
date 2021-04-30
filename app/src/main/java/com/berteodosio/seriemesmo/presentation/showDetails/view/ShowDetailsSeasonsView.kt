package com.berteodosio.seriemesmo.presentation.showDetails.view

import com.berteodosio.seriemesmo.domain.model.Season

interface ShowDetailsSeasonsView {

    fun initialize()

    fun displaySeasons(seasons: List<Season>)

    fun navigateToSeasonDetailsScreen(
        season: Season,
        showId: Long
    )

}