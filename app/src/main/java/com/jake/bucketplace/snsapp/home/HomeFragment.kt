package com.jake.bucketplace.snsapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var swipeContainer: SwipeRefreshLayout

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            this@HomeFragment.swipeContainer = homeSwipeContainer
        }


        return binding.root
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    override fun onRefresh() {

    }
}