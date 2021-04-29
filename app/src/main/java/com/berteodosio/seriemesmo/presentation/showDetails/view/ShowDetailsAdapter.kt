package com.berteodosio.seriemesmo.presentation.showDetails.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class ShowDetailsAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        // TODO: improve this code
        return if (position % 2 == 0) {
            ShowDetailsInfoFragment.newInstance()
        } else {
            ShowDetailsSeasonsFragment.newInstance()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence {
        return if (position % 2 == 0) "INFO" else "SEASONS"     // TODO: extract to strings file
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}