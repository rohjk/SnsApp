package com.jake.bucketplace.snsapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var adapter: HomeListAdapter

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
        adapter = HomeListAdapter(emptyList())
        binding.apply {
            lifecycleOwner = this@HomeFragment
            viewModel = this@HomeFragment.viewModel
            homeListView.adapter = adapter
            this@HomeFragment.swipeContainer = homeSwipeContainer
        }

        subscribeUI()
        return binding.root
    }

    private fun subscribeUI() {
        viewModel.apply {
            home.observe(viewLifecycleOwner) {
                adapter.sumbitHome(it)
            }
            onError.observe(viewLifecycleOwner) { errorMessage ->
                Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        binding?.unbind()
        super.onDestroy()
    }

}