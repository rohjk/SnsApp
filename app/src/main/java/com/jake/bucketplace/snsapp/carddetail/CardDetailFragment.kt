package com.jake.bucketplace.snsapp.carddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.databinding.FragmentCardDetailBinding
import javax.inject.Inject

class CardDetailFragment : Fragment() {

    private var _binding: FragmentCardDetailBinding? = null
    private val binding get() = _binding!!
    private val args: CardDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CardDetailViewModel by viewModels { viewModelFactory }

    private lateinit var adapter: CardDetailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as SnsApplication).appComponent.cardDeatilComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = FragmentCardDetailBinding.inflate(inflater, container, false)
        adapter = CardDetailListAdapter(emptyList())
        binding.apply {
            lifecycleOwner = this@CardDetailFragment
            viewModel = this@CardDetailFragment.viewModel
            cardDetailListView.adapter = this@CardDetailFragment.adapter
        }

        subscribeUI()
        viewModel.setCardId(args.cardId)

        return binding.root
    }

    private fun subscribeUI() {
        viewModel.apply {
            cardDetail.observe(viewLifecycleOwner) { cardDetail ->
                adapter.sumbitCardDetail(cardDetail)
            }
            onError.observe(viewLifecycleOwner) { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}