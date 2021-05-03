package com.berteodosio.seriemesmo.presentation.showDetails.view

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Show

class ShowDetailsAdapter(
    private val show: Show,
    fragmentManager: FragmentManager,
    private val resources: Resources
) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return if (position % 2 == 0) {
            ShowDetailsInfoFragment.newInstance(show)
        } else {
            ShowDetailsSeasonsFragment.newInstance(show)
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence {
        @StringRes val titleRes = if (position % 2 == 0) R.string.show_details_page_title_info else R.string.show_details_page_title_seasons
        return resources.getString(titleRes)
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}