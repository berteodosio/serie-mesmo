package com.berteodosio.seriemesmo.presentation.showDetails.presenter

import com.berteodosio.seriemesmo.domain.useCase.model.Show
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.showDetails.view.ShowDetailsSeasonsView

class ShowDetailsSeasonsPresenter(private val view: ShowDetailsSeasonsView) : BasePresenter() {

    fun onInitialize(show: Show?) {
        if (show == null) {
            // TODO HANDLE ERROR
            return
        }

        view.displaySeasons(show.seasons)
    }
}