package com.jake.bucketplace.snsapp.photofeed

import dagger.Subcomponent

@Subcomponent
interface PhotoFeedComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): PhotoFeedComponent
    }

    fun inject(fragment: PhotoFeedFragment)
}