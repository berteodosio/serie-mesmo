package com.berteodosio.seriemesmo.presentation.showDetails.presenter

import com.berteodosio.seriemesmo.domain.useCase.base.setupCommonSchedulers
import com.berteodosio.seriemesmo.domain.useCase.show.FetchShowDetailsUseCase
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import com.berteodosio.seriemesmo.presentation.showDetails.view.ShowDetailsView

class ShowDetailsPresenter(
    private val view: ShowDetailsView,
    private val fetchShowDetailsUseCase: FetchShowDetailsUseCase
) : BasePresenter() {

    fun onInitialize(showId: Long) {
        view.initialize()
        if (showId < 0) {
            // TODO: handle error and improve code
            return
        }

        fetchShowDetails(showId)
    }

    private fun fetchShowDetails(showId: Long) {
        view.showLoading()
        val disposable = fetchShowDetailsUseCase
            .execute(showId)
            .setupCommonSchedulers()
            .subscribe(
                { show -> view.displayShowDetails(show)},
                { e -> AppLogger.e(TAG, "Error fetching Show Details for $showId", e); view.hideLoading() }     // TODO: show error
            )

        addDisposable(disposable)
    }
}