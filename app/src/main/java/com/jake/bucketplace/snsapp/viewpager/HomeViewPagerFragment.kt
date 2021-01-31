package com.jake.bucketplace.snsapp.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.databinding.FragmentHomeViewPagerBinding

class HomeViewPagerFragment : Fragment() {

    private var _binding: FragmentHomeViewPagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var tabs: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        binding.apply {
            this@HomeViewPagerFragment.tabs = homeTabs
            this@HomeViewPagerFragment.viewPager = homeViewPager
        }

        viewPager.adapter = HomeViewPagerAdapter(this)

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when(position) {
            HOME_PAGE_INDEX -> getString(R.string.home)
            PHOTO_FEED_PAGE_INDEX -> getString(R.string.photo_feed)
            else -> null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}