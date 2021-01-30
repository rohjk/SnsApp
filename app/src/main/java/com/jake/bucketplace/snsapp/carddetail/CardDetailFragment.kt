package com.jake.bucketplace.snsapp.carddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.jake.bucketplace.snsapp.databinding.FragmentCardDetailBinding

class CardDetailFragment : Fragment() {

    private lateinit var binding: FragmentCardDetailBinding
    val args: CardDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       this.binding = FragmentCardDetailBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@CardDetailFragment
        }

        Toast.makeText(activity, "Card Id : ${args.cardId}", Toast.LENGTH_SHORT).show()

        return binding.root
    }

}