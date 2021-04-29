package com.berteodosio.seriemesmo.presentation.showDetails.view

import com.berteodosio.seriemesmo.domain.useCase.model.Show

interface ShowDetailsView {

    fun initialize()

    fun showLoading()

    fun hideLoading()

    fun displayShowDetails(show: Show)

}