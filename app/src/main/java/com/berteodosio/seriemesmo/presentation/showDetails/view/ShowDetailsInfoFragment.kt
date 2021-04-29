package com.berteodosio.seriemesmo.presentation.showDetails.view

import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseFragment
import com.berteodosio.seriemesmo.presentation.showDetails.presenter.ShowDetailsInfoPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class ShowDetailsInfoFragment : BaseFragment<ShowDetailsInfoPresenter>() {

    override fun fragmentModule(): Kodein.Module = Kodein.Module("ShowDetailsInfo Module") {
        bind<BasePresenter>() with provider { ShowDetailsInfoPresenter() }
    }


    companion object {
        fun newInstance() = ShowDetailsInfoFragment()
    }
}