package com.berteodosio.seriemesmo.presentation.home.view

import android.os.Bundle
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.useCase.model.Show
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.custom.view.hide
import com.berteodosio.seriemesmo.presentation.custom.view.show
import com.berteodosio.seriemesmo.presentation.home.adapter.HomeShowsAdapter
import com.berteodosio.seriemesmo.presentation.home.presenter.HomePresenter
import com.berteodosio.seriemesmo.presentation.showDetails.view.ShowDetailsActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class HomeActivity : BaseAppCompatActivity<HomePresenter>(), HomeView {

    private val showsAdapter by lazy { HomeShowsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.onInitialize()
    }

    override fun activityModule(): Kodein.Module = Kodein.Module("Home Module") {
        bind<BasePresenter>() with provider { HomePresenter(this@HomeActivity, instance()) }
    }

    override fun initialize() {
        shows_recycler?.adapter = showsAdapter
        showsAdapter.addOnShowClickListener { presenter.onShowClick(it) }
    }

    override fun showLoading() {
        home_loading?.show()
    }

    override fun hideLoading() {
        home_loading?.hide()
    }

    override fun displayShow(show: Show) {
        showsAdapter.addShow(show)
    }

    override fun navigateToShowDetailsScreen(showId: Long) {
        startActivity(ShowDetailsActivity.newIntent(this, showId))
    }
}