package com.berteodosio.seriemesmo.presentation.home.viewModel

sealed class HomeNavigationEvent {

    class NavigateToShowDetails(val showId: Long) : HomeNavigationEvent()

}