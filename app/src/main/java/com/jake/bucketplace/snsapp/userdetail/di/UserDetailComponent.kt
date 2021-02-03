package com.jake.bucketplace.snsapp.userdetail.di

import com.jake.bucketplace.snsapp.di.FragmentScope
import com.jake.bucketplace.snsapp.userdetail.UserDetailFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [UserDetailModule::class])
interface UserDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserDetailComponent
    }

    fun inject(fragment: UserDetailFragment)
}