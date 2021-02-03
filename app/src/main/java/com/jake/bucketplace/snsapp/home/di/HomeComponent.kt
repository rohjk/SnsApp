package com.jake.bucketplace.snsapp.home.di

import com.jake.bucketplace.snsapp.di.FragmentScope
import com.jake.bucketplace.snsapp.home.HomeFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(fragment: HomeFragment)
}