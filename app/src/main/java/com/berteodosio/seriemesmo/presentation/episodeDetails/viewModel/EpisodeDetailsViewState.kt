package com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel

sealed class EpisodeDetailsViewState {

    class Idle(
        val episodeName: String,
        val episodeOverview: String,
        val episodeCoverUrl: String,
        val episodeAirDate: String,
        val episodeNumber: Long
    ) : EpisodeDetailsViewState()

}