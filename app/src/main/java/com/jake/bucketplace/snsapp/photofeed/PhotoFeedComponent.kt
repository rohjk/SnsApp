package com.jake.bucketplace.snsapp.photofeed

import com.jake.bucketplace.snsapp.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface PhotoFeedComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): PhotoFeedComponent
    }

    fun inject(fragment: PhotoFeedFragment)
}