package com.berteodosio.seriemesmo.presentation.home.view

import com.berteodosio.seriemesmo.domain.usecase.model.Show

interface HomeView {

    fun showLoading()

    fun displayShow(show: Show)

    fun initialize()

}