package com.jake.bucketplace.snsapp.di

import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.carddetail.CardDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CardDetailViewModel::class)
    abstract fun bindCardDetailViewModel(vm: CardDetailViewModel) : ViewModel
}