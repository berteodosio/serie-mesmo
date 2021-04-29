package com.berteodosio.seriemesmo.presentation.showDetails.view

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.useCase.model.Show
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import com.berteodosio.seriemesmo.presentation.base.view.BaseAppCompatActivity
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import com.berteodosio.seriemesmo.presentation.showDetails.presenter.ShowDetailsPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_show_details.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class ShowDetailsActivity : BaseAppCompatActivity<ShowDetailsPresenter>(), ShowDetailsView {

    override fun activityModule(): Kodein.Module = Kodein.Module("Show Details Module") {
        bind<BasePresenter>() with provider {
            ShowDetailsPresenter(
                this@ShowDetailsActivity,
                instance()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        val showId = intent?.getLongExtra(EXTRA_SHOW_ID, INVALID_SHOW_ID) ?: INVALID_SHOW_ID
        presenter.onInitialize(showId = showId)
    }

    override fun initialize() {
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(show_details_toolbar)
    }

    override fun showLoading() {
//        TODO("Not yet implemented")
    }

    override fun hideLoading() {
//        TODO("Not yet implemented")
    }

    override fun displayShowDetails(show: Show) {
        setupTabs(show)
    }

    private fun setupTabs(show: Show) {
        val adapter = ShowDetailsAdapter(show, supportFragmentManager)
        show_details_viewpager?.adapter = adapter
        // TODO: check commented code
//        show_details_viewpager?.addOnPageChangeListener(ViewPagerOnPageSelectedListener { onPageSelected(adapter.get(it)) })
        show_details_tablayout?.setupWithViewPager(show_details_viewpager)
        onPageSelected(show)
    }

    // TODO check
    private fun onPageSelected(show: Show) {
        show_details_toolbar?.title = show.name
        AppLogger.d(TAG, "BackdropURL: ${show.backdropUrl}")
        Glide.with(this)
            .load(show.backdropUrl)
            .transition(DrawableTransitionOptions.withCrossFade().clone())
            .apply(
                RequestOptions()
                    .centerCrop()
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
            )
            .into(cover_image)

//        animatedUpdateColor(newColorRes = candidate.color)
    }

    private fun animatedUpdateColor(@ColorRes newColorRes: Int) {
        val oldColor = (collapsing_toolbar_layout?.contentScrim as? ColorDrawable)?.color
        val newColor = ContextCompat.getColor(this, newColorRes)

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), oldColor, newColor)
        colorAnimation.duration = 250 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            val animatedValue = animator.animatedValue as Int
            collapsing_toolbar_layout?.setContentScrimColor(animatedValue)
            collapsing_toolbar_layout?.setStatusBarScrimColor(animatedValue)
        }
        colorAnimation.start()
    }

    companion object {
        private const val EXTRA_SHOW_ID = "EXTRA_SHOW_ID"
        private const val INVALID_SHOW_ID = -1L

        fun newIntent(context: Context, showId: Long): Intent =
            Intent(context, ShowDetailsActivity::class.java)
                .putExtra(EXTRA_SHOW_ID, showId)
    }

}