package com.berteodosio.seriemesmo.presentation.episodeDetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.episodeDetails.presenter.EpisodeDetailsPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class EpisodeDetailsActivity : BaseAppCompatActivity<EpisodeDetailsPresenter>(), EpisodeDetailsView {

    override fun activityModule(): Kodein.Module = Kodein.Module("Episode Details Module") {
        bind<BasePresenter>() with provider { EpisodeDetailsPresenter() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_details)
        presenter.onInitialize()
    }

    companion object {

        private const val EXTRA_SHOW_ID = "EXTRA_SHOW_ID"
        private const val EXTRA_SEASON_NUMBER = "EXTRA_SEASON_NUMBER"
        private const val EXTRA_EPISODE_NUMBER = "EXTRA_EPISODE_NUMBER"

        private const val INVALID_VALUE = -1L

        fun newIntent(context: Context, showId: Long, seasonNumber: Long, episodeNumber: Long) =
            Intent(context, EpisodeDetailsActivity::class.java)
                .putExtra(EXTRA_SHOW_ID, showId)
                .putExtra(EXTRA_SEASON_NUMBER, seasonNumber)
                .putExtra(EXTRA_EPISODE_NUMBER, episodeNumber)
    }
}