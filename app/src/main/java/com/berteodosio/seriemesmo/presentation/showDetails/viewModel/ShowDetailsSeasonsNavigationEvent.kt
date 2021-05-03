package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import com.berteodosio.seriemesmo.domain.model.Season

sealed class ShowDetailsSeasonsNavigationEvent {

    class NavigateToSeasonDetails(val season: Season, val showId: Long) : ShowDetailsSeasonsNavigationEvent()

}