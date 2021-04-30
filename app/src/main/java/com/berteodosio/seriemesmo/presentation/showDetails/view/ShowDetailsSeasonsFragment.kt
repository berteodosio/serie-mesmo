package com.berteodosio.seriemesmo.presentation.showDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Season
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseFragment
import com.berteodosio.seriemesmo.presentation.seasonDetails.view.SeasonDetailsActivity
import com.berteodosio.seriemesmo.presentation.showDetails.adapter.ShowDetailsSeasonsAdapter
import com.berteodosio.seriemesmo.presentation.showDetails.presenter.ShowDetailsSeasonsPresenter
import kotlinx.android.synthetic.main.fragment_show_details_seasons.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class ShowDetailsSeasonsFragment : BaseFragment<ShowDetailsSeasonsPresenter>(),
    ShowDetailsSeasonsView {

    private val showDetailsSeasonsAdapter by lazy { ShowDetailsSeasonsAdapter() }

    override fun fragmentModule(): Kodein.Module = Kodein.Module("ShowDetailsSeasons Module") {
        bind<BasePresenter>() with provider { ShowDetailsSeasonsPresenter(this@ShowDetailsSeasonsFragment) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_details_seasons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val show = arguments?.getParcelable<Show?>(ShowDetailsSeasonsFragment.ARGUMENT_SHOW)
        presenter.onInitialize(show)
    }

    override fun initialize() {
        showDetailsSeasonsAdapter.addOnClickListener { presenter.onSeasonClick(it) }
    }

    override fun displaySeasons(seasons: List<Season>) {
        seasons_recycler?.adapter = showDetailsSeasonsAdapter
        showDetailsSeasonsAdapter.addSeasons(seasons)
    }

    override fun navigateToSeasonDetailsScreen(season: Season, showId: Long) {
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