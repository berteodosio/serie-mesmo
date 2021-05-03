package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import com.berteodosio.seriemesmo.domain.model.Show

sealed class ShowDetailsViewState {

    object Loading : ShowDetailsViewState()

    class ShowDetailsLoaded(val show: Show) : ShowDetailsViewState()

    class Error(e: Throwable) : ShowDetailsViewState()

}