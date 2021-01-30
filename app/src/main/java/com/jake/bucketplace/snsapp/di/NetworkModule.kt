package com.jake.bucketplace.snsapp.di

import android.content.Context
import com.jake.bucketplace.snsapp.data.network.CardServiceApi
import com.jake.bucketplace.snsapp.data.network.HomeServiceApi
import com.jake.bucketplace.snsapp.data.network.UserServiceApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun providesBucketPlaceRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl("http://35.200.92.60:11000/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideHomeServiceApi(retrofit: Retrofit): HomeServiceApi {
        return retrofit.create(HomeServiceApi::class.java)
    }

    @Provides
    fun provideCardServiceApi(retrofit: Retrofit): CardServiceApi {
        return retrofit.create(CardServiceApi::class.java)
    }

    @Provides
    fun provideUserServiceApi(retrofit: Retrofit): UserServiceApi {
        return retrofit.create(UserServiceApi::class.java)
    }

}