package com.jake.bucketplace.snsapp.userdetail

import dagger.Subcomponent

@Subcomponent
interface UserDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserDetailComponent
    }

    fun inject(fragment: UserDetailFragment)
}