package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import com.berteodosio.seriemesmo.domain.model.Season

sealed class ShowDetailsSeasonsViewState {

    class DisplayingSeasons(val seasons: List<Season>) : ShowDetailsSeasonsViewState()

    object Error : ShowDetailsSeasonsViewState()

}