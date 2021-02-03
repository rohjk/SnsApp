package com.jake.bucketplace.snsapp.carddetail.di

import com.jake.bucketplace.snsapp.carddetail.CardDetailFragment
import com.jake.bucketplace.snsapp.di.FragmentScope
import dagger.Subcomponent

@Subcomponent(modules = [CardDetailModule::class])
interface CardDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CardDetailComponent
    }

    fun inject(fragment: CardDetailFragment)
}