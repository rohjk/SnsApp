package com.jake.bucketplace.snsapp.userdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.jake.bucketplace.snsapp.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private val args: UserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@UserDetailFragment
        }

        Toast.makeText(context, "User ID : ${args.userId}", Toast.LENGTH_SHORT).show()

        return binding.root
    }

}