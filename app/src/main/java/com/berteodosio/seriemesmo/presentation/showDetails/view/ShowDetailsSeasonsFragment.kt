package com.berteodosio.seriemesmo.presentation.showDetails.view

import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseFragment
import com.berteodosio.seriemesmo.presentation.showDetails.presenter.ShowDetailsInfoPresenter
import com.berteodosio.seriemesmo.presentation.showDetails.presenter.ShowDetailsSeasonsPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class ShowDetailsSeasonsFragment : BaseFragment<ShowDetailsSeasonsPresenter>() {

    override fun fragmentModule(): Kodein.Module = Kodein.Module("ShowDetailsSeasons Module") {
        bind<BasePresenter>() with provider { ShowDetailsSeasonsPresenter() }
    }

    companion object {
        fun newInstance() = ShowDetailsSeasonsFragment()
    }

}