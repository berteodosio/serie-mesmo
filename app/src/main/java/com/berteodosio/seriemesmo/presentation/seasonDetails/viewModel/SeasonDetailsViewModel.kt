package com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.domain.model.Episode
import com.berteodosio.seriemesmo.domain.useCase.base.setupCommonSchedulers
import com.berteodosio.seriemesmo.domain.useCase.season.FetchSeasonDetailsUseCase
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger

class SeasonDetailsViewModel(
    private val showId: Long,
    private val seasonNumber: Long,
    private val seasonName: String,
    private val fetchSeasonDetailsUseCase: FetchSeasonDetailsUseCase
) : BaseViewModel() {

    private val _viewState = MutableLiveData<SeasonDetailsViewState>()
    val viewState: LiveData<SeasonDetailsViewState>
        get() = _viewState

    private val _navigationEvents = MutableLiveData<SeasonDetailsNavigationEvent>()
    val navigationEvents: LiveData<SeasonDetailsNavigationEvent>
        get() = _navigationEvents

    init {
        fetchSeasonDetails()
    }

    private fun setLoadingState() {
        _viewState.value = SeasonDetailsViewState.Loading(seasonName)
    }

    private fun fetchSeasonDetails() {
        setLoadingState()
        val disposable = fetchSeasonDetailsUseCase
            .execute(showId, seasonNumber)
            .setupCommonSchedulers()
            .subscribe(
                { season -> _viewState.value = SeasonDetailsViewState.SeasonLoaded(season) },
                { e ->
                    AppLogger.e(
                        TAG,
                        "Error loading season details",
                        e
                    )

                    _viewState.value = SeasonDetailsViewState.Error(e)
                })      // TODO properly handle error

        addDisposable(disposable)
    }

    fun onEpisodeClick(episode: Episode) {
        _navigationEvents.value = SeasonDetailsNavigationEvent.NavigateToEpisodeDetails(episode)
    }

}