package com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EpisodeDetailsViewModelFactory(
    private val episodeName: String,
    private val episodeOverview: String,
    private val episodeCoverUrl: String,
    private val episodeAirDate: String,
    private val episodeNumber: Long
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpisodeDetailsViewModel(episodeName, episodeOverview, episodeCoverUrl, episodeAirDate, episodeNumber) as T
    }
}