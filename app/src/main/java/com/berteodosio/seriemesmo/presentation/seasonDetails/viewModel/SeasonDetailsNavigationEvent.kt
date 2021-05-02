package com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel

import com.berteodosio.seriemesmo.domain.model.Episode

sealed class SeasonDetailsNavigationEvent {

    class NavigateToEpisodeDetails(val episode: Episode) : SeasonDetailsNavigationEvent()

}