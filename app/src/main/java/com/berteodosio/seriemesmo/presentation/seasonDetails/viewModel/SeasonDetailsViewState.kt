package com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel

import com.berteodosio.seriemesmo.domain.model.Season

sealed class SeasonDetailsViewState {

    object Initial : SeasonDetailsViewState()

    data class Loading(val seasonName: String) : SeasonDetailsViewState()

    data class DisplayingContent(val season: Season) : SeasonDetailsViewState()

    class Error(e: Throwable) : SeasonDetailsViewState()

}