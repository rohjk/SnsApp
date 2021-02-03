package com.jake.bucketplace.snsapp.signin.di

import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.ViewModelKey
import com.jake.bucketplace.snsapp.signin.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SignInModule {
    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(vm: SignInViewModel) : ViewModel

}