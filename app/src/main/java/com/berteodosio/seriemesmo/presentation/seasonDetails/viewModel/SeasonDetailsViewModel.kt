package com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.domain.model.Episode
import com.berteodosio.seriemesmo.domain.useCase.season.FetchSeasonDetailsUseCase
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import com.hadilq.liveevent.LiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SeasonDetailsViewModel(
    private val showId: Long,
    private val seasonNumber: Long,
    private val seasonName: String,
    private val fetchSeasonDetailsUseCase: FetchSeasonDetailsUseCase
) : BaseViewModel() {

    private val _viewState = MutableLiveData<SeasonDetailsViewState>()
    val viewState: LiveData<SeasonDetailsViewState>
        get() = _viewState

    private val _navigationEvents = LiveEvent<SeasonDetailsNavigationEvent>()
    val navigationEvents: LiveData<SeasonDetailsNavigationEvent>
        get() = _navigationEvents

    init {
        _viewState.value = SeasonDetailsViewState.Initial
    }

    /*
     * The creation of this method was needed for testing purposes. We could've done
     * the fetchSeasonDetails() call on the init block, but then it wouldn't be possible
     * to test it.
     */
    fun onInitialization() {
        if (_viewState.value != SeasonDetailsViewState.Initial) {
            return
        }

        fetchSeasonDetails()
    }

    private fun setLoadingState() {
        _viewState.value = SeasonDetailsViewState.Loading(seasonName)
    }

    private fun fetchSeasonDetails() {
        setLoadingState()
        val disposable = fetchSeasonDetailsUseCase
            .execute(showId, seasonNumber)
                // TODO: SETUP COMMON SCHEDULERS
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { season -> _viewState.value = SeasonDetailsViewState.DisplayingContent(season) },
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