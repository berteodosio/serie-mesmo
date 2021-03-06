package com.berteodosio.seriemesmo.presentation.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.domain.useCase.base.setupCommonSchedulers
import com.berteodosio.seriemesmo.domain.useCase.show.FetchPopularShowsUseCase
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel.SeasonDetailsViewState
import com.hadilq.liveevent.LiveEvent

class HomeViewModel(
    private val fetchPopularShowsUseCase: FetchPopularShowsUseCase
) : BaseViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState>
        get() = _viewState

    private val _navigationEvents = LiveEvent<HomeNavigationEvent>()
    val navigationEvents: LiveData<HomeNavigationEvent>
        get() = _navigationEvents

    init {
        _viewState.value = HomeViewState.Initial
    }

    /*
     * The creation of this method was needed for testing purposes. We could've done
     * the fetchPopularShows() call on the init block, but then it wouldn't be possible
     * to test it.
     */
    fun onInitialization() {
        if (_viewState.value != HomeViewState.Initial) {
            return
        }

        fetchPopularShows()
    }

    private fun fetchPopularShows() {
        setLoadingState()
        val disposable = fetchPopularShowsUseCase
            .execute()
            .setupCommonSchedulers()
            .toList()
            .subscribe(
                {
                    AppLogger.d(TAG, "Show list fetched");
                    _viewState.value = HomeViewState.DisplayingShows(it)
                },
                {
                    AppLogger.e(TAG, "An error happened while fetching shows", it)
                    _viewState.value = HomeViewState.Error(it)
                }
            )

        addDisposable(disposable)
    }

    private fun setLoadingState() {
        _viewState.value = HomeViewState.Loading
    }

    fun onShowClick(show: Show) {
        _navigationEvents.value = HomeNavigationEvent.NavigateToShowDetails(showId = show.id)
    }
}