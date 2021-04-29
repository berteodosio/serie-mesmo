package com.berteodosio.seriemesmo.presentation.showDetails.presenter

import com.berteodosio.seriemesmo.domain.useCase.model.Show
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.showDetails.view.ShowDetailsInfoView

class ShowDetailsInfoPresenter(private val view: ShowDetailsInfoView) : BasePresenter() {

    fun onInitialize(show: Show?) {

        // TODO: properly handle
        if (show == null) {
            return
        }
        view.displayShowDetails(show)
    }
}