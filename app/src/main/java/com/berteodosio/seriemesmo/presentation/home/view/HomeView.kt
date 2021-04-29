package com.berteodosio.seriemesmo.presentation.home.view

import com.berteodosio.seriemesmo.domain.useCase.model.Show

interface HomeView {

    fun showLoading()

    fun hideLoading()

    fun displayShow(show: Show)

    fun initialize()

    fun navigateToShowDetailsScreen(showId: Long)

}