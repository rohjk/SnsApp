package com.jake.bucketplace.snsapp.signup.di

import com.jake.bucketplace.snsapp.di.FragmentScope
import com.jake.bucketplace.snsapp.signup.SignUpFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [SignUpModule::class])
interface SignUpComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SignUpComponent
    }

    fun inject(fragment: SignUpFragment)
}