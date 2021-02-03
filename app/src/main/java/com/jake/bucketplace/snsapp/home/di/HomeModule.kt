package com.jake.bucketplace.snsapp.home.di

import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.ViewModelKey
import com.jake.bucketplace.snsapp.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(vm: HomeViewModel) : ViewModel
}