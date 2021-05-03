package com.berteodosio.seriemesmo.presentation.showDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Season
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseFragment
import com.berteodosio.seriemesmo.presentation.base.view.BaseFragmentWithPresenter
import com.berteodosio.seriemesmo.presentation.seasonDetails.view.SeasonDetailsActivity
import com.berteodosio.seriemesmo.presentation.showDetails.adapter.ShowDetailsSeasonsAdapter
import com.berteodosio.seriemesmo.presentation.showDetails.presenter.ShowDetailsSeasonsPresenter
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsSeasonsNavigationEvent
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsSeasonsViewModel
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsSeasonsViewModelFactory
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsSeasonsViewState
import kotlinx.android.synthetic.main.fragment_show_details_seasons.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class ShowDetailsSeasonsFragment : BaseFragment() {

    private val showDetailsSeasonsAdapter by lazy { ShowDetailsSeasonsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_details_seasons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val show = arguments?.getParcelable<Show?>(ARGUMENT_SHOW)

        val viewModel: ShowDetailsSeasonsViewModel by activityViewModels { ShowDetailsSeasonsViewModelFactory(show) }
        viewModel.viewState.observe(viewLifecycleOwner, Observer { onViewStateChanged(it) })
        viewModel.navigationEvents.observe(viewLifecycleOwner, Observer { onNavigationEventReceived(it) })

        showDetailsSeasonsAdapter.addOnClickListener { viewModel.onSeasonClick(it) }
    }

    private fun onNavigationEventReceived(event: ShowDetailsSeasonsNavigationEvent) = when (event) {
        is ShowDetailsSeasonsNavigationEvent.NavigateToSeasonDetails -> navigateToSeasonDetailsScreen(event.season, event.showId)
    }

    private fun onViewStateChanged(viewState: ShowDetailsSeasonsViewState) = when (viewState) {
        is ShowDetailsSeasonsViewState.Idle -> displaySeasons(viewState.seasons)
        ShowDetailsSeasonsViewState.Error -> {
            // TODO: showError
        }
    }

    private fun displaySeasons(seasons: List<Season>) {
        seasons_recycler?.adapter = showDetailsSeasonsAdapter
        showDetailsSeasonsAdapter.addSeasons(seasons)
    }

    private fun navigateToSeasonDetailsScreen(season: Season, showId: Long) {
        val context = this.context ?: return
        startActivity(SeasonDetailsActivity.newIntent(context, showId, season.number, season.name))
    }

    companion object {

        private const val ARGUMENT_SHOW = "ARGUMENT_SHOW"

        fun newInstance(show: Show) = ShowDetailsSeasonsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ShowDetailsSeasonsFragment.ARGUMENT_SHOW, show)
            }
        }
    }

}