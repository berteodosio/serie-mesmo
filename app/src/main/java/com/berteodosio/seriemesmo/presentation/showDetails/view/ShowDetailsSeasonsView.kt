package com.berteodosio.seriemesmo.presentation.showDetails.view

import com.berteodosio.seriemesmo.domain.useCase.model.Season

interface ShowDetailsSeasonsView {

    fun displaySeasons(seasons: List<Season>)

}