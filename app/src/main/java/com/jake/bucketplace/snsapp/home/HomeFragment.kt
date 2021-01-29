package com.jake.bucketplace.snsapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var swipeContainer: SwipeRefreshLayout
    private val popularCardListAdapter: PopularCardListAdapter by lazy {
        PopularCardListAdapter(emptyList())
    }
    private val popularUserListAdapter: PopularUserListAdapter by lazy {
        PopularUserListAdapter(emptyList())
    }
    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as SnsApplication).appComponent.homeComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@HomeFragment
            viewModel = this@HomeFragment.viewModel
            homePapularCardsListView.adapter = popularCardListAdapter
            homePapularUsersListView.adapter = popularUserListAdapter
            this@HomeFragment.swipeContainer = homeSwipeContainer
        }
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI() {
        viewModel.popularCards.observe(viewLifecycleOwner) {
            popularCardListAdapter.update(it)
        }
        viewModel.popularUsers.observe(viewLifecycleOwner) {
            popularUserListAdapter.update(it)
        }
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

}