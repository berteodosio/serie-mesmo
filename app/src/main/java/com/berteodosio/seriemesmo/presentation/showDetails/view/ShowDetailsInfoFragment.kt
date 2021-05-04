package com.berteodosio.seriemesmo.presentation.showDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.view.BaseFragment
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsInfoViewModel
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsInfoViewModelFactory
import com.berteodosio.seriemesmo.presentation.showDetails.viewModel.ShowDetailsInfoViewState
import kotlinx.android.synthetic.main.fragment_show_details_info.*

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
        is ShowDetailsInfoViewState.DisplayingContent -> displayShowDetails(viewState.show)
        ShowDetailsInfoViewState.Error -> Unit
    }

    private fun displayShowDetails(show: Show) {
        show_overview_text?.text = show.overview
        show_genres_text?.text = createGenresListText(show.genres)
        show_details_text?.text = mountDetailsText(show)
    }

    private fun mountDetailsText(show: Show): String {
        return context?.getString(R.string.show_details_info_card_text, show.status, show.originalLanguage) ?: ""
    }

    private fun createGenresListText(genres: List<String>): String {
        if (genres.isEmpty()) return ""

        if (genres.size == 1) return genres.first()

        return genres.joinToString(separator = " | ")
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