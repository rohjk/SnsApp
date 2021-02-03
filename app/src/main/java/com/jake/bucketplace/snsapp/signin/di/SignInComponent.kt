package com.jake.bucketplace.snsapp.signin.di

import com.jake.bucketplace.snsapp.di.FragmentScope
import com.jake.bucketplace.snsapp.signin.SignInFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [SignInModule::class])
interface SignInComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SignInComponent
    }

    fun inject(fragment: SignInFragment)
}