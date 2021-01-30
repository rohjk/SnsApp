package com.jake.bucketplace.snsapp.userdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jake.bucketplace.snsapp.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@UserDetailFragment
        }
        return binding.root
    }

}