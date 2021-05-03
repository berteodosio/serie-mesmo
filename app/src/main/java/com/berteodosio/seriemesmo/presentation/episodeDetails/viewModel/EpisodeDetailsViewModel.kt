package com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel

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
        setDisplayingContent()
    }

    private fun setDisplayingContent() {
        _viewState.value = EpisodeDetailsViewState.DisplayingContent(episodeName, episodeOverview, episodeCoverUrl, episodeAirDate, episodeNumber)
    }

}