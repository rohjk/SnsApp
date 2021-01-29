package com.jake.bucketplace.snsapp.home

import dagger.Subcomponent

@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(fragment: HomeFragment)
}