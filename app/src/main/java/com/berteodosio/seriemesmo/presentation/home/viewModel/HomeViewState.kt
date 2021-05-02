package com.berteodosio.seriemesmo.presentation.home.viewModel

import com.berteodosio.seriemesmo.domain.model.Show

sealed class HomeViewState {

    object Loading : HomeViewState()

    class Idle(val shows: List<Show>) : HomeViewState()

    class Error(e: Throwable) : HomeViewState()

}