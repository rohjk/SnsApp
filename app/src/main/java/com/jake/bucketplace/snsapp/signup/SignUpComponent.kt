package com.jake.bucketplace.snsapp.signup

import dagger.Subcomponent

@Subcomponent
interface SignUpComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SignUpComponent
    }

    fun inject(fragment: SignUpFragment)
}