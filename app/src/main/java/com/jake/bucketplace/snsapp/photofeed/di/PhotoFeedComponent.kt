package com.jake.bucketplace.snsapp.photofeed.di

import com.jake.bucketplace.snsapp.di.FragmentScope
import com.jake.bucketplace.snsapp.photofeed.PhotoFeedFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PhotoFeedModule::class])
interface PhotoFeedComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): PhotoFeedComponent
    }

    fun inject(fragment: PhotoFeedFragment)
}