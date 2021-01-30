package com.jake.bucketplace.snsapp.photofeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.databinding.FragmentPhotoFeedBinding

class PhotoFeedFragment : Fragment() {

    private lateinit var binding: FragmentPhotoFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       this.binding = FragmentPhotoFeedBinding.inflate(inflater, container, false)
       return binding.root
    }

}