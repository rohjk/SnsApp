package com.jake.bucketplace.snsapp.di

import com.jake.bucketplace.snsapp.data.network.HomeServiceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesBucketPlaceRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://35.200.92.60:11000")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideHomeServiceApi(retrofit: Retrofit): HomeServiceApi {
        return retrofit.create(HomeServiceApi::class.java)
    }

}