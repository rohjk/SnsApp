package com.jake.bucketplace.snsapp.signup

import com.jake.bucketplace.snsapp.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface SignUpComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SignUpComponent
    }

    fun inject(fragment: SignUpFragment)
}