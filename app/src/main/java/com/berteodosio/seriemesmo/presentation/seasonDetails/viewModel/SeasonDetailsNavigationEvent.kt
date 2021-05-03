package com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel

import com.berteodosio.seriemesmo.domain.model.Episode

sealed class SeasonDetailsNavigationEvent {

    open class NavigateToEpisodeDetails(val episode: Episode) : SeasonDetailsNavigationEvent()

}