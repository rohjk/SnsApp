package com.jake.bucketplace.snsapp.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jake.bucketplace.snsapp.home.HomeFragment
import com.jake.bucketplace.snsapp.photofeed.PhotoFeedFragment
import java.lang.IndexOutOfBoundsException

const val HOME_PAGE_INDEX = 0
const val PHOTO_FEED_PAGE_INDEX = 1

class HomeViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val tabFragmentsCreator: Map<Int, () -> Fragment> = mapOf(
        HOME_PAGE_INDEX to { HomeFragment() },
        PHOTO_FEED_PAGE_INDEX to {PhotoFeedFragment()}
    )

    override fun getItemCount(): Int {
        return tabFragmentsCreator.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}
