package com.jake.bucketplace.snsapp.photofeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.databinding.FragmentPhotoFeedBinding
import javax.inject.Inject

class PhotoFeedFragment : Fragment() {

    private lateinit var binding: FragmentPhotoFeedBinding

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
        return binding.root
    }

}