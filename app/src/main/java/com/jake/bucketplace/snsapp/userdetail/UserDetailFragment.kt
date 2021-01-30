package com.jake.bucketplace.snsapp.userdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.databinding.FragmentUserDetailBinding
import javax.inject.Inject

class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private val args: UserDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewmodel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as SnsApplication).appComponent.userDeatilComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@UserDetailFragment
        }

        viewmodel.setUserId(args.userId)
        return binding.root
    }

    private fun subscribeUI() {
    }

}