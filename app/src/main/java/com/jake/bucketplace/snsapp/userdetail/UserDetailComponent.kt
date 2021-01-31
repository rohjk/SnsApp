package com.jake.bucketplace.snsapp.userdetail

import com.jake.bucketplace.snsapp.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface UserDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserDetailComponent
    }

    fun inject(fragment: UserDetailFragment)
}