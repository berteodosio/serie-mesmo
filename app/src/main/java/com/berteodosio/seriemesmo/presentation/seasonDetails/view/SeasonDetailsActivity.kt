package com.berteodosio.seriemesmo.presentation.seasonDetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.transition.TransitionManager
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Season
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import com.berteodosio.seriemesmo.presentation.custom.view.hide
import com.berteodosio.seriemesmo.presentation.custom.view.loadCenterCropCrossFade
import com.berteodosio.seriemesmo.presentation.custom.view.show
import com.berteodosio.seriemesmo.presentation.episodeDetails.view.EpisodeDetailsActivity
import com.berteodosio.seriemesmo.presentation.seasonDetails.adapter.SeasonDetailsAdapter
import com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel.SeasonDetailsNavigationEvent
import com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel.SeasonDetailsViewModel
import com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel.SeasonDetailsViewModelFactory
import com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel.SeasonDetailsViewState
import kotlinx.android.synthetic.main.activity_season_details.*

class SeasonDetailsActivity : BaseAppCompatActivity() {

    private val seasonDetailsAdapter by lazy { SeasonDetailsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_details)
        initializeToolbar()

        val showId = intent?.getLongExtra(EXTRA_SHOW_ID, INVALID_VALUE) ?: INVALID_VALUE
        val seasonNumber = intent?.getLongExtra(EXTRA_SEASON_NUMBER, INVALID_VALUE) ?: INVALID_VALUE
        val seasonName = intent?.getStringExtra(EXTRA_SEASON_NAME) ?: ""

        val viewModel: SeasonDetailsViewModel by viewModels { SeasonDetailsViewModelFactory(showId, seasonNumber, seasonName, instance()) }
        viewModel.viewState.observe(this, Observer { viewState -> onViewStateChanged(viewState) })
        viewModel.navigationEvents.observe(this, Observer { onNavigationEventReceived(it) })

        seasonDetailsAdapter.setOnClickListener(viewModel::onEpisodeClick)
        viewModel.onInitialization()
    }

    private fun initializeToolbar() {
        setSupportActionBar(season_details_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupToolbarFont()
    }

    private fun setupToolbarFont() {
        val montserratTypeface = ResourcesCompat.getFont(this, R.font.montserrat)
        collapsing_toolbar_layout?.apply {
            setCollapsedTitleTypeface(montserratTypeface)
            setExpandedTitleTypeface(montserratTypeface)
        }
    }

    private fun onNavigationEventReceived(event: SeasonDetailsNavigationEvent) {
        return when (event) {
            is SeasonDetailsNavigationEvent.NavigateToEpisodeDetails -> {
                val episode = event.episode
                navigateToEpisodeDetailsScreen(episode.name, episode.overview, episode.episodeImageUrl, episode.airDate, episode.number)
            }
        }
    }

    private fun onViewStateChanged(viewState: SeasonDetailsViewState) = when (viewState) {
        is SeasonDetailsViewState.Loading -> {
            setupTitle(viewState.seasonName)
            showLoading()
        }
        is SeasonDetailsViewState.DisplayingContent -> {
            hideErrorLayout()
            displaySeasonDetails(viewState.season)
            hideLoading()
        }
        SeasonDetailsViewState.Error -> {
            hideLoading()
            showErrorLayout()
        }
        SeasonDetailsViewState.Initial -> Unit
    }

    private fun hideErrorLayout() {
        season_details_error_text?.hide()
        season_details_recycler?.show()
    }

    private fun showErrorLayout() {
        season_details_recycler?.hide()
        season_details_error_text?.show()
    }

    private fun setupTitle(seasonName: String) {
        season_details_toolbar?.title = seasonName
        title = seasonName
    }

    private fun showLoading() {
        season_details_recycler?.hide()
        season_details_loading?.show()
    }

    private fun hideLoading() {
        TransitionManager.beginDelayedTransition(season_details_layout)
        season_details_loading?.hide()
        season_details_recycler?.show()
    }

    private fun displaySeasonDetails(season: Season) {
        setupTitle(season.name)
        displayCoverImage(season)
        seasonDetailsAdapter.addEpisodes(season.episodes)
        season_details_recycler?.adapter = seasonDetailsAdapter
    }

    private fun displayCoverImage(season: Season) {
        AppLogger.i(TAG, "Displaying season image: ${season.posterUrl}")
        season_cover_image?.loadCenterCropCrossFade(season.posterUrl)
    }

    private fun navigateToEpisodeDetailsScreen(episodeName: String, episodeOverview: String, episodeCoverUrl: String, episodeAirDate: String, episodeNumber: Long) {
        startActivity(EpisodeDetailsActivity.newIntent(this, episodeName, episodeOverview, episodeCoverUrl, episodeAirDate, episodeNumber))
    }

    companion object {

        private const val EXTRA_SHOW_ID = "EXTRA_SHOW_ID"
        private const val EXTRA_SEASON_NUMBER = "EXTRA_SEASON_NUMBER"
        private const val EXTRA_SEASON_NAME = "EXTRA_SEASON_NAME"

        private const val INVALID_VALUE = -1L

        fun newIntent(context: Context, showId: Long, seasonNumber: Long, seasonName: String) =
            Intent(context, SeasonDetailsActivity::class.java)
                .putExtra(EXTRA_SHOW_ID, showId)
                .putExtra(EXTRA_SEASON_NUMBER, seasonNumber)
                .putExtra(EXTRA_SEASON_NAME, seasonName)
    }
}