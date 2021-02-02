package com.jake.bucketplace.snsapp.photofeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.adapters.VerticalCardListAdapter
import com.jake.bucketplace.snsapp.carddetail.CardDetailViewModel
import com.jake.bucketplace.snsapp.databinding.FragmentPhotoFeedBinding
import javax.inject.Inject

class PhotoFeedFragment : Fragment() {

    private var _binding: FragmentPhotoFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterCard: VerticalCardListAdapter
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PhotoFeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as SnsApplication).appComponent.photoFeedComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = FragmentPhotoFeedBinding.inflate(inflater, container, false)
        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(PhotoFeedViewModel::class.java)
        this.adapterCard = VerticalCardListAdapter(emptyList())
        binding.apply {
            lifecycleOwner = this@PhotoFeedFragment
            viewModel = this@PhotoFeedFragment.viewModel
            photoFeedListView.adapter = adapterCard
            this@PhotoFeedFragment.recyclerView = photoFeedListView
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (isLastIndex(layoutManager)) {
                    viewModel.loadMore()
                }
            }
        })

        subscribeUI()
        viewModel.loadCard()
        return binding.root
    }

    private fun subscribeUI() {
        viewModel.apply {
            cards.observe(viewLifecycleOwner) { items ->
                adapterCard.update(items)
            }
            onError.observe(viewLifecycleOwner) { errorMessage ->
                Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isLastIndex(layoutManager: LinearLayoutManager): Boolean {
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        return visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}