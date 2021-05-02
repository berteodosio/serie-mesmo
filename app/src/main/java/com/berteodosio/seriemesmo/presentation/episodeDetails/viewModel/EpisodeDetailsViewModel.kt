package com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel
import com.berteodosio.seriemesmo.presentation.episodeDetails.presenter.EpisodeDetailsViewState

class EpisodeDetailsViewModel(
    private val episodeName: String,
    private val episodeOverview: String,
    private val episodeCoverUrl: String,
    private val episodeAirDate: String,
    private val episodeNumber: Long
) : BaseViewModel() {

    private val _viewState = MutableLiveData<EpisodeDetailsViewState>()

    val viewState: LiveData<EpisodeDetailsViewState>
        get() = _viewState

    init {
        setIdleState()
    }

    private fun setIdleState() {
        _viewState.value = EpisodeDetailsViewState.Idle(episodeName, episodeOverview, episodeCoverUrl, episodeAirDate, episodeNumber)
    }

}