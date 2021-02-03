package com.jake.bucketplace.snsapp.photofeed.di

import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.ViewModelKey
import com.jake.bucketplace.snsapp.photofeed.PhotoFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PhotoFeedModule {
    @Binds
    @IntoMap
    @ViewModelKey(PhotoFeedViewModel::class)
    abstract fun bindPhotoFeedViewModel(vm: PhotoFeedViewModel) : ViewModel
}