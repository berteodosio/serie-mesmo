package com.berteodosio.seriemesmo.presentation.home.presenter

import com.berteodosio.seriemesmo.domain.usecase.base.setupCommonSchedulers
import com.berteodosio.seriemesmo.domain.usecase.show.FetchPopularShowsUseCase
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import com.berteodosio.seriemesmo.presentation.home.view.HomeView

class HomePresenter(private val view: HomeView,
                    private val fetchPopularShowsUseCase: FetchPopularShowsUseCase
) : BasePresenter() {

    fun onInitialize() {
        view.initialize()
        fetchShows()
    }

    private fun fetchShows() {
        view.showLoading()
        val disposable = fetchPopularShowsUseCase
            .execute()
            .setupCommonSchedulers()
            .subscribe(
                { AppLogger.i("FetchShows", "Show fetched: ${it.name}"); view.displayShow(it) },
                { AppLogger.e("FetchShows", "An error happened while fetching shows", it) })

        addDisposable(disposable)
    }
}