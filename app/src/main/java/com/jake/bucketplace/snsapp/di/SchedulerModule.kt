package com.jake.bucketplace.snsapp.di

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier annotation class MainScheduler
@Qualifier annotation class IOScheduler

@Module
class SchedulerModule {

    @Singleton
    @Provides
    @MainScheduler
    fun provideMainScheduler() = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    @IOScheduler
    fun provideIOScheduler() = Schedulers.io()

}