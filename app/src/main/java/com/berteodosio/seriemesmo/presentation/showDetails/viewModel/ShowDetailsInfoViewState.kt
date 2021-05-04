package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import com.berteodosio.seriemesmo.domain.model.Show

sealed class ShowDetailsInfoViewState {

    class DisplayingContent(val show: Show) : ShowDetailsInfoViewState()

    object Error : ShowDetailsInfoViewState()

}