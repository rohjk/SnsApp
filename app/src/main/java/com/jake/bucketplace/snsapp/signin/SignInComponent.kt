package com.jake.bucketplace.snsapp.signin

import com.jake.bucketplace.snsapp.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface SignInComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SignInComponent
    }

    fun inject(fragment: SignInFragment)
}