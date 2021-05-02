package com.berteodosio.seriemesmo.presentation.episodeDetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivityWithPresenter
import com.berteodosio.seriemesmo.presentation.custom.view.loadCenterCrop
import com.berteodosio.seriemesmo.presentation.episodeDetails.presenter.EpisodeDetailsPresenter
import kotlinx.android.synthetic.main.activity_episode_details.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class EpisodeDetailsActivity : BaseAppCompatActivityWithPresenter<EpisodeDetailsPresenter>(), EpisodeDetailsView {

    override fun activityModule(): Kodein.Module = Kodein.Module("Episode Details Module") {
        bind<BasePresenter>() with provider { EpisodeDetailsPresenter(this@EpisodeDetailsActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_details)

        val episodeName = intent?.getStringExtra(EXTRA_NAME) ?: ""
        val episodeOverview = intent?.getStringExtra(EXTRA_OVERVIEW) ?: ""
        val episodeCoverUrl = intent?.getStringExtra(EXTRA_COVER_URL) ?: ""
        val episodeAirDate = intent?.getStringExtra(EXTRA_AIR_DATE) ?: ""
        val episodeNumber = intent?.getLongExtra(EXTRA_EPISODE_NUMBER, INVALID_VALUE) ?: INVALID_VALUE
        presenter.onInitialize(episodeName, episodeOverview, episodeCoverUrl, episodeAirDate, episodeNumber)
    }

    override fun initialize() {
        setSupportActionBar(episode_details_toolbar)
    }

    override fun displayEpisodeDetails(
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

        fun newIntent(context: Context, name: String, overview: String, coverUrl: String, airDate: String, episodeNumber: Long) =
            Intent(context, EpisodeDetailsActivity::class.java)
                .putExtra(EXTRA_NAME, name)
                .putExtra(EXTRA_OVERVIEW, overview)
                .putExtra(EXTRA_COVER_URL, coverUrl)
                .putExtra(EXTRA_AIR_DATE, airDate)
                .putExtra(EXTRA_EPISODE_NUMBER, episodeNumber)
    }
}