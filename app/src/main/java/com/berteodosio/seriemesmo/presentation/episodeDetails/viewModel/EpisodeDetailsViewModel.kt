package com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel
import com.berteodosio.seriemesmo.presentation.episodeDetails.formatter.DateFormatter
import java.text.SimpleDateFormat
import java.util.*

/*
 * Although this ViewModel has no practical use (since EpisodeDetails is a very simple feature),
 * it exists because it may be useful in the future, when the screen gets expanded with
 * additional information and maybe additional features.
 */
class EpisodeDetailsViewModel(
    private val episodeName: String,
    private val episodeOverview: String,
    private val episodeCoverUrl: String,
    private val episodeAirDate: String,
    private val episodeNumber: Long,
    private val dateFormatter: DateFormatter
) : BaseViewModel() {

    private val _viewState = MutableLiveData<EpisodeDetailsViewState>()

    val viewState: LiveData<EpisodeDetailsViewState>
        get() = _viewState

    init {
        setDisplayingContent()
    }

    private fun setDisplayingContent() {
        _viewState.value = EpisodeDetailsViewState.DisplayingContent(episodeName, episodeOverview, episodeCoverUrl,
            formatEpisodeAirDate(), episodeNumber)
    }

    private fun formatEpisodeAirDate(): String = dateFormatter.format(episodeAirDate)

}