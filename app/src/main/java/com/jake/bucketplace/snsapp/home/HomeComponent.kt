package com.jake.bucketplace.snsapp.home

import com.jake.bucketplace.snsapp.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(fragment: HomeFragment)
}