package com.jake.bucketplace.snsapp.di

import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(vm: MainViewModel) : ViewModel
}