package com.berteodosio.seriemesmo.presentation.showDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseFragment
import com.berteodosio.seriemesmo.presentation.base.view.BaseFragmentWithPresenter
import com.berteodosio.seriemesmo.presentation.showDetails.presenter.ShowDetailsInfoPresenter
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsInfoViewModel
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsInfoViewModelFactory
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsInfoViewState
import kotlinx.android.synthetic.main.fragment_show_details_info.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class ShowDetailsInfoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_details_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val show = arguments?.getParcelable<Show?>(ARGUMENT_SHOW)

        val viewModel: ShowDetailsInfoViewModel by activityViewModels { ShowDetailsInfoViewModelFactory(show) }
        viewModel.viewState.observe(viewLifecycleOwner, Observer { onViewStateChanged(it) })
    }

    private fun onViewStateChanged(viewState: ShowDetailsInfoViewState) = when (viewState) {
        is ShowDetailsInfoViewState.Idle -> displayShowDetails(viewState.show)
        ShowDetailsInfoViewState.Error -> {
            // TODO: display error
        }
    }

    private fun displayShowDetails(show: Show) {
        show_overview_text?.text = show.overview
        show_genres_text?.text = createGenresListText(show.genres)
        show_details_text?.text = mountDetailsText(show)
    }

    private fun mountDetailsText(show: Show): String {
        return "Status: ${show.status}\nOriginal Language: ${show.originalLanguage}"        // TODO EXTRACT STRING RESOURCE
    }

    private fun createGenresListText(genres: List<String>): String {
        if (genres.isEmpty()) return ""

        if (genres.size == 1) return genres.first()

        // TODO: improve this
        return genres.first() + genres.drop(1).map { " | $it" }.joinToString { it }
    }

    companion object {

        private const val ARGUMENT_SHOW = "ARGUMENT_SHOW"

        fun newInstance(show: Show) = ShowDetailsInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_SHOW, show)
            }
        }
    }
}