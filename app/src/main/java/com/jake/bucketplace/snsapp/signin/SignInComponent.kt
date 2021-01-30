package com.jake.bucketplace.snsapp.signin

import dagger.Subcomponent

@Subcomponent
interface SignInComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SignInComponent
    }

    fun inject(fragment: SignInFragment)
}