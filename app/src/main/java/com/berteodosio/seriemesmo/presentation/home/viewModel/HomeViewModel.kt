package com.berteodosio.seriemesmo.presentation.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.domain.useCase.base.setupCommonSchedulers
import com.berteodosio.seriemesmo.domain.useCase.show.FetchPopularShowsUseCase
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger

class HomeViewModel(
    private val fetchPopularShowsUseCase: FetchPopularShowsUseCase
) : BaseViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState>
        get() = _viewState

    private val _navigationEvents = MutableLiveData<HomeNavigationEvent>()
    val navigationEvents: LiveData<HomeNavigationEvent>
        get() = _navigationEvents

    init {
        _viewState.value = HomeViewState.Loading
        fetchPopularShows()
    }

    private fun fetchPopularShows() {
        val disposable = fetchPopularShowsUseCase
            .execute()
            .setupCommonSchedulers()
            .toList()                   // TODO: remove toList()
            .subscribe(
                {
                    AppLogger.d(TAG, "Show list fetched");
                    _viewState.value = HomeViewState.Idle(it)
                },
                {
                    AppLogger.e(TAG, "An error happened while fetching shows", it)
                    _viewState.value = HomeViewState.Error(it)              // TODO: show error
                }
            )

        addDisposable(disposable)
    }

    fun onShowClick(show: Show) {
        _navigationEvents.value = HomeNavigationEvent.NavigateToShowDetails(showId = show.id)
    }
}