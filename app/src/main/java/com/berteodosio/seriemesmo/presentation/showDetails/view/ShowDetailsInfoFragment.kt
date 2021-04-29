package com.berteodosio.seriemesmo.presentation.showDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseFragment
import com.berteodosio.seriemesmo.presentation.showDetails.presenter.ShowDetailsInfoPresenter
import kotlinx.android.synthetic.main.fragment_show_details_info.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class ShowDetailsInfoFragment : BaseFragment<ShowDetailsInfoPresenter>(), ShowDetailsInfoView {

    override fun fragmentModule(): Kodein.Module = Kodein.Module("ShowDetailsInfo Module") {
        bind<BasePresenter>() with provider { ShowDetailsInfoPresenter(this@ShowDetailsInfoFragment) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_details_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val showOverview = arguments?.getString(ARGUMENT_OVERVIEW) ?: ""
        presenter.onInitialize(showOverview)
    }

    override fun displayShowDetails(showOverview: String) {
        show_overview_text?.text = showOverview
    }

    companion object {

        private const val ARGUMENT_OVERVIEW = "ARGUMENT_OVERVIEW"

        fun newInstance(showOverView: String) = ShowDetailsInfoFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_OVERVIEW, showOverView)
            }
        }
    }
}