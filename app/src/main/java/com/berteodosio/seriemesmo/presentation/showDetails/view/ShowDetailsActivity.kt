package com.berteodosio.seriemesmo.presentation.showDetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.domain.useCase.show.FetchShowDetailsUseCase
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import com.berteodosio.seriemesmo.presentation.custom.view.hide
import com.berteodosio.seriemesmo.presentation.custom.view.show
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsViewModel
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsViewModelFactory
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsViewState
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_show_details.*
import org.kodein.di.generic.instance

class ShowDetailsActivity : BaseAppCompatActivity() {

    private val fetchShowDetailsUseCase: FetchShowDetailsUseCase by instance<FetchShowDetailsUseCase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        setSupportActionBar(show_details_toolbar)

        val showId = intent?.getLongExtra(EXTRA_SHOW_ID, INVALID_SHOW_ID) ?: INVALID_SHOW_ID

        val viewModel: ShowDetailsViewModel by viewModels {
            ShowDetailsViewModelFactory(
                showId,
                fetchShowDetailsUseCase
            )
        }
        viewModel.viewState.observe(this, Observer { onViewStateChanged(it) })
    }

    private fun onViewStateChanged(viewState: ShowDetailsViewState) = when (viewState) {
        ShowDetailsViewState.Loading -> showLoading()
        is ShowDetailsViewState.ShowDetailsLoaded -> {
            displayShowDetails(viewState.show)
            hideLoading()
        }

        is ShowDetailsViewState.Error -> {
            hideLoading()
            // TODO: HANDLE ERROR STATE
        }
    }

    private fun setupTabLayoutCustomFonts() {
        for (i in 0..show_details_tablayout.tabCount) {
            val inflatedTextView = LayoutInflater.from(this).inflate(R.layout.custom_textview_font, null) as? TextView?
            show_details_tablayout.getTabAt(i)?.customView = inflatedTextView;
        }
    }

    private fun showLoading() {
        show_details_viewpager?.hide()
        show_details_loading?.show()
    }

    private fun hideLoading() {
        show_details_loading?.hide()
        show_details_viewpager?.show()
    }

    private fun displayShowDetails(show: Show) {
        setupTabs(show)
        setupTabLayoutCustomFonts()
    }

    private fun setupTabs(show: Show) {
        val adapter = ShowDetailsAdapter(show, supportFragmentManager, resources)
        show_details_viewpager?.adapter = adapter
        show_details_tablayout?.setupWithViewPager(show_details_viewpager)
        onPageSelected(show)
    }

    private fun onPageSelected(show: Show) {
        show_details_toolbar?.title = show.name
        Glide.with(this)
            .load(show.backdropUrl)
            .transition(DrawableTransitionOptions.withCrossFade().clone())
            .apply(RequestOptions().centerCrop())
            .into(cover_image)
    }

    companion object {
        private const val EXTRA_SHOW_ID = "EXTRA_SHOW_ID"
        private const val INVALID_SHOW_ID = -1L

        fun newIntent(context: Context, showId: Long): Intent =
            Intent(context, ShowDetailsActivity::class.java)
                .putExtra(EXTRA_SHOW_ID, showId)
    }

}