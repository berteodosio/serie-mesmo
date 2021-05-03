package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.domain.model.Season
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel
import com.hadilq.liveevent.LiveEvent

class ShowDetailsSeasonsViewModel(private val show: Show?) : BaseViewModel() {

    private val _viewState = MutableLiveData<ShowDetailsSeasonsViewState>()
    val viewState: LiveData<ShowDetailsSeasonsViewState>
        get() = _viewState

    private val _navigationEvents = LiveEvent<ShowDetailsSeasonsNavigationEvent>()
    val navigationEvents: LiveData<ShowDetailsSeasonsNavigationEvent>
        get() = _navigationEvents

    init {
        displaySeasons()
    }

    private fun displaySeasons() {
        if (show == null) {
            _viewState.value = ShowDetailsSeasonsViewState.Error
            return
        }

        _viewState.value = ShowDetailsSeasonsViewState.DisplayingSeasons(show.seasons)
    }

    fun onSeasonClick(season: Season) {
        val showId = show?.id ?: return
        _navigationEvents.value = ShowDetailsSeasonsNavigationEvent.NavigateToSeasonDetails(season, showId)
    }

}