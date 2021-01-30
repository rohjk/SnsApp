package com.jake.bucketplace.snsapp.carddetail

import dagger.Subcomponent

@Subcomponent
interface CardDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CardDetailComponent
    }

    fun inject(fragment: CardDetailFragment)
}