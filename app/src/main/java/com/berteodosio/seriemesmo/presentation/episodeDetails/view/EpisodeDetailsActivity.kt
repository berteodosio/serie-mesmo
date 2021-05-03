package com.berteodosio.seriemesmo.presentation.episodeDetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.custom.view.loadCenterCrop
import com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel.EpisodeDetailsViewState
import com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel.EpisodeDetailsViewModel
import com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel.EpisodeDetailsViewModelFactory
import kotlinx.android.synthetic.main.activity_episode_details.*

class EpisodeDetailsActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_details)

        val episodeName = intent?.getStringExtra(EXTRA_NAME) ?: ""
        val episodeOverview = intent?.getStringExtra(EXTRA_OVERVIEW) ?: ""
        val episodeCoverUrl = intent?.getStringExtra(EXTRA_COVER_URL) ?: ""
        val episodeAirDate = intent?.getStringExtra(EXTRA_AIR_DATE) ?: ""
        val episodeNumber = intent?.getLongExtra(EXTRA_EPISODE_NUMBER, INVALID_VALUE) ?: INVALID_VALUE

        val viewModel: EpisodeDetailsViewModel by viewModels {
            EpisodeDetailsViewModelFactory(
                episodeName,
                episodeOverview,
                episodeCoverUrl,
                episodeAirDate,
                episodeNumber
            )
        }
        viewModel.viewState.observe(this, Observer { onViewStateChanged(it) })
    }

    private fun onViewStateChanged(viewState: EpisodeDetailsViewState) = when (viewState) {
        is EpisodeDetailsViewState.DisplayingContent -> {
            setSupportActionBar(episode_details_toolbar)
            displayEpisodeDetails(
                viewState.episodeName,
                viewState.episodeOverview,
                viewState.episodeCoverUrl,
                viewState.episodeAirDate,
                viewState.episodeNumber
            )
        }
    }

    private fun displayEpisodeDetails(
        name: String,
        overview: String,
        coverUrl: String,
        airDate: String,
        number: Long
    ) {
        episode_title?.text = name
        episode_overview_text?.text = overview
        episode_time?.text = airDate
        episode_cover_image?.loadCenterCrop(coverUrl)

        // TODO fix warning
        val episodeNumberText = getString(R.string.episode_details_title, "$number")
        episode_details_toolbar?.title = episodeNumberText
        title = episodeNumberText
    }

    companion object {

        private const val EXTRA_NAME = "EXTRA_NAME"
        private const val EXTRA_OVERVIEW = "EXTRA_OVERVIEW"
        private const val EXTRA_COVER_URL = "EXTRA_COVER_URL"
        private const val EXTRA_AIR_DATE = "EXTRA_AIR_DATE"
        private const val EXTRA_EPISODE_NUMBER = "EXTRA_EPISODE_NUMBER"

        private const val INVALID_VALUE = -1L

        fun newIntent(
            context: Context,
            name: String,
            overview: String,
            coverUrl: String,
            airDate: String,
            episodeNumber: Long
        ) =
            Intent(context, EpisodeDetailsActivity::class.java)
                .putExtra(EXTRA_NAME, name)
                .putExtra(EXTRA_OVERVIEW, overview)
                .putExtra(EXTRA_COVER_URL, coverUrl)
                .putExtra(EXTRA_AIR_DATE, airDate)
                .putExtra(EXTRA_EPISODE_NUMBER, episodeNumber)
    }
}