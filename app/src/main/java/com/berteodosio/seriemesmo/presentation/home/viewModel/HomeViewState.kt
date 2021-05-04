package com.berteodosio.seriemesmo.presentation.home.viewModel

import com.berteodosio.seriemesmo.domain.model.Show

sealed class HomeViewState {

    object Initial : HomeViewState()

    object Loading : HomeViewState()

    class DisplayingShows(val shows: List<Show>) : HomeViewState()

    class Error(val e: Throwable) : HomeViewState()

}