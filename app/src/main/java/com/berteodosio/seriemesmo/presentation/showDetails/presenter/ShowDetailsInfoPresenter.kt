package com.berteodosio.seriemesmo.presentation.showDetails.presenter

import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.showDetails.view.ShowDetailsInfoView

class ShowDetailsInfoPresenter(private val view: ShowDetailsInfoView) : BasePresenter() {

    fun onInitialize(showOverview: String) {
        view.displayShowDetails(showOverview)
    }
}