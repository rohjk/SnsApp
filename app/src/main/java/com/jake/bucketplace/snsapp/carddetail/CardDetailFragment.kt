package com.jake.bucketplace.snsapp.carddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.databinding.FragmentCardDetailBinding
import javax.inject.Inject

class CardDetailFragment : Fragment() {

    private lateinit var binding: FragmentCardDetailBinding
    val args: CardDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: CardDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as SnsApplication).appComponent.cardDeatilComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       this.binding = FragmentCardDetailBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@CardDetailFragment
        }

        viewModel.setCardId(args.cardId)

        return binding.root
    }

}