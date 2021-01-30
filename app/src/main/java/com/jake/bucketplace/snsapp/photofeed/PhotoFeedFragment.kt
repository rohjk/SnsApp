package com.jake.bucketplace.snsapp.photofeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.adapters.CardVerticalListAdapter
import com.jake.bucketplace.snsapp.databinding.FragmentPhotoFeedBinding
import javax.inject.Inject

class PhotoFeedFragment : Fragment() {

    private lateinit var binding: FragmentPhotoFeedBinding
    private lateinit var adapter: CardVerticalListAdapter
    private lateinit var recyclerView: RecyclerView
    @Inject
    lateinit var viewModel: PhotoFeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as SnsApplication).appComponent.photoFeedComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentPhotoFeedBinding.inflate(inflater, container, false)
        this.adapter = CardVerticalListAdapter(emptyList())
        binding.apply {
            lifecycleOwner = this@PhotoFeedFragment
            viewModel = this@PhotoFeedFragment.viewModel
            photoFeedListView.adapter = adapter
            this@PhotoFeedFragment.recyclerView = photoFeedListView
        }

        subscribeUI()
        return binding.root
    }

    private fun subscribeUI() {
        viewModel.cards.observe(viewLifecycleOwner) { items ->
            adapter.update(items)
        }
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

}