package com.berteodosio.seriemesmo.presentation.seasonDetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Season
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import com.berteodosio.seriemesmo.presentation.seasonDetails.adapter.SeasonDetailsAdapter
import com.berteodosio.seriemesmo.presentation.seasonDetails.presenter.SeasonDetailsPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_season_details.*
import kotlinx.android.synthetic.main.activity_show_details.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SeasonDetailsActivity : BaseAppCompatActivity<SeasonDetailsPresenter>(), SeasonDetailsView {

    private val seasonDetailsAdapter by lazy { SeasonDetailsAdapter() }

    override fun activityModule(): Kodein.Module = Kodein.Module("Season Details Module") {
        bind<BasePresenter>() with provider {
            SeasonDetailsPresenter(
                this@SeasonDetailsActivity,
                instance()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_details)
        val showId = intent?.getLongExtra(EXTRA_SHOW_ID, INVALID_VALUE) ?: INVALID_VALUE
        val seasonNumber = intent?.getLongExtra(EXTRA_SEASON_NUMBER, INVALID_VALUE) ?: INVALID_VALUE
        val seasonName = intent?.getStringExtra(EXTRA_SEASON_NAME) ?: ""

        presenter.onInitialize(showId, seasonNumber, seasonName)
    }

    override fun initialize(seasonName: String) {
        setupToolbar(seasonName)
    }

    private fun setupToolbar(seasonName: String) {
        setSupportActionBar(season_details_toolbar)
        season_details_toolbar?.title = seasonName
        title = seasonName
    }

    override fun showLoading() {
        // TODO("Not yet implemented")
    }

    override fun displaySeasonDetails(season: Season) {
        displayCoverImage(season)
        seasonDetailsAdapter.addEpisodes(season.episodes)
        season_details_recycler?.adapter = seasonDetailsAdapter
    }

    private fun displayCoverImage(season: Season) {
        AppLogger.i(TAG, "Displaying season image: ${season.posterUrl}")
        Glide.with(this)
            .load(season.posterUrl)
            .transition(DrawableTransitionOptions.withCrossFade().clone())
            .apply(RequestOptions().centerCrop())
            .into(season_cover_image)
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