package com.jake.bucketplace.snsapp.userdetail.di

import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.ViewModelKey
import com.jake.bucketplace.snsapp.userdetail.UserDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserDetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    abstract fun bindUserDetailViewModel(vm: UserDetailViewModel) : ViewModel
}