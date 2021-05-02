package com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel

import com.berteodosio.seriemesmo.domain.model.Season

sealed class SeasonDetailsViewState {

    class Loading(val seasonName: String) : SeasonDetailsViewState()

    class SeasonLoaded(val season: Season) : SeasonDetailsViewState()

    class Error(e: Throwable) : SeasonDetailsViewState()

}