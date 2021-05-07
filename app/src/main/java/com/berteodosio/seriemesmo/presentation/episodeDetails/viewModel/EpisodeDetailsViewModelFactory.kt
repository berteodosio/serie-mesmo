package com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.berteodosio.seriemesmo.presentation.episodeDetails.formatter.DateFormatter

class EpisodeDetailsViewModelFactory(
    private val episodeName: String,
    private val episodeOverview: String,
    private val episodeCoverUrl: String,
    private val episodeAirDate: String,
    private val episodeNumber: Long,
    private val formatter: DateFormatter
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpisodeDetailsViewModel(episodeName, episodeOverview, episodeCoverUrl, episodeAirDate, episodeNumber, formatter) as T
    }
}