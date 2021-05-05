package com.berteodosio.seriemesmo.presentation.home.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.custom.view.hide
import com.berteodosio.seriemesmo.presentation.custom.view.show
import com.berteodosio.seriemesmo.presentation.home.adapter.HomeShowsAdapter
import com.berteodosio.seriemesmo.presentation.home.viewModel.HomeNavigationEvent
import com.berteodosio.seriemesmo.presentation.home.viewModel.HomeViewModel
import com.berteodosio.seriemesmo.presentation.home.viewModel.HomeViewModelFactory
import com.berteodosio.seriemesmo.presentation.home.viewModel.HomeViewState
import com.berteodosio.seriemesmo.presentation.showDetails.view.ShowDetailsActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseAppCompatActivity() {

    private val showsAdapter by lazy { HomeShowsAdapter(windowManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(home_toolbar)

        val viewModel: HomeViewModel by viewModels { HomeViewModelFactory(instance()) }
        viewModel.viewState.observe(this, Observer { onViewStateChanged(it) })
        viewModel.navigationEvents.observe(this, Observer { onNavigationEventReceived(it) })

        showsAdapter.setOnShowClickListener(viewModel::onShowClick)
        viewModel.onInitialization()
    }

    private fun onNavigationEventReceived(event: HomeNavigationEvent): Unit = when (event) {
        is HomeNavigationEvent.NavigateToShowDetails -> navigateToShowDetailsScreen(showId = event.showId)
    }

    private fun onViewStateChanged(viewState: HomeViewState) {
        return when (viewState) {
            HomeViewState.Loading -> showLoading()
            is HomeViewState.DisplayingShows -> {
                hideErrorLayout()
                hideLoading()
                setupAdapter()
                displayShows(viewState.shows)
            }
            is HomeViewState.Error -> {
                hideLoading()
                showErrorLayout()
            }
            HomeViewState.Initial -> Unit
        }
    }

    private fun hideErrorLayout() {
        home_error_text?.hide()
        shows_recycler?.show()
    }

    private fun showErrorLayout() {
        shows_recycler?.hide()
        home_error_text?.show()
    }

    private fun displayShows(shows: List<Show>) {
        shows.forEach(::displayShow)
    }

    private fun showLoading() {
        home_loading?.show()
    }

    private fun hideLoading() {
        home_loading?.hide()
    }

    private fun setupAdapter() {
        shows_recycler?.adapter = showsAdapter
    }

    private fun displayShow(show: Show) {
        showsAdapter.addShow(show)
    }

    private fun navigateToShowDetailsScreen(showId: Long) {
        startActivity(ShowDetailsActivity.newIntent(this, showId))
    }
}