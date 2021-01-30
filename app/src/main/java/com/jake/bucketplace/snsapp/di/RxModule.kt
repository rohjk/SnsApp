package com.jake.bucketplace.snsapp.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class RxModule {
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}