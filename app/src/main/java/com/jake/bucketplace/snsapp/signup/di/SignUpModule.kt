package com.jake.bucketplace.snsapp.signup.di

import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.ViewModelKey
import com.jake.bucketplace.snsapp.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SignUpModule {
    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(vm: SignUpViewModel) : ViewModel
}
