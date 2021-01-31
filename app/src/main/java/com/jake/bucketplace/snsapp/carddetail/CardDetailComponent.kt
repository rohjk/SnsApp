package com.jake.bucketplace.snsapp.carddetail

import com.jake.bucketplace.snsapp.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface CardDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CardDetailComponent
    }

    fun inject(fragment: CardDetailFragment)
}