package com.jake.bucketplace.snsapp.carddetail.di

import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.carddetail.CardDetailViewModel
import com.jake.bucketplace.snsapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CardDetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(CardDetailViewModel::class)
    abstract fun bindCardDetailViewModel(vm: CardDetailViewModel): ViewModel
}
