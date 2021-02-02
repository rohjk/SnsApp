package com.jake.bucketplace.snsapp.di

import androidx.lifecycle.ViewModelProvider
import com.jake.bucketplace.snsapp.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}

