package com.berteodosio.seriemesmo.presentation.showDetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionManager
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.custom.view.hide
import com.berteodosio.seriemesmo.presentation.custom.view.loadCenterCropCrossFade
import com.berteodosio.seriemesmo.presentation.custom.view.show
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsViewModel
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsViewModelFactory
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsViewState
import kotlinx.android.synthetic.main.activity_show_details.*

class ShowDetailsActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        setSupportActionBar(show_details_toolbar)

        val showId = intent?.getLongExtra(EXTRA_SHOW_ID, INVALID_SHOW_ID) ?: INVALID_SHOW_ID

        val viewModel: ShowDetailsViewModel by viewModels {
            ShowDetailsViewModelFactory(
                showId,
                instance()
            )
        }
        viewModel.viewState.observe(this, Observer { onViewStateChanged(it) })
    }

    private fun onViewStateChanged(viewState: ShowDetailsViewState) = when (viewState) {
        ShowDetailsViewState.Loading -> showLoading()
        is ShowDetailsViewState.ShowDetailsLoaded -> {
            hideErrorLayout()
            displayShowDetails(viewState.show)
            hideLoading()
        }

        is ShowDetailsViewState.Error -> {
            hideLoading()
            showErrorLayout()
        }
    }

    private fun hideErrorLayout() {
        show_details_error_text?.hide()
        show_details_viewpager?.show()
    }

    private fun showErrorLayout() {
        show_details_viewpager?.hide()
        show_details_error_text?.show()
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
        TransitionManager.beginDelayedTransition(show_details_frame)
        show_details_loading?.hide()
        show_details_viewpager?.show()
    }

    private fun displayShowDetails(show: Show) {
        setupTitle(show)
        setupTabs(show)
        setupTabLayoutCustomFonts()
    }

    private fun setupTitle(show: Show) {
        show_details_toolbar?.title = show.name
        title = show.name
    }

    private fun setupTabs(show: Show) {
        val adapter = ShowDetailsAdapter(show, supportFragmentManager, resources)
        show_details_viewpager?.adapter = adapter
        show_details_tablayout?.setupWithViewPager(show_details_viewpager)
        onPageSelected(show)
    }

    private fun onPageSelected(show: Show) {
        setupTitle(show)
        cover_image?.loadCenterCropCrossFade(show.backdropUrl)
    }

    companion object {
        private const val EXTRA_SHOW_ID = "EXTRA_SHOW_ID"
        private const val INVALID_SHOW_ID = -1L

        fun newIntent(context: Context, showId: Long): Intent =
            Intent(context, ShowDetailsActivity::class.java)
                .putExtra(EXTRA_SHOW_ID, showId)
    }

}