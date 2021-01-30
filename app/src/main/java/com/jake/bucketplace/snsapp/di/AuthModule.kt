package com.jake.bucketplace.snsapp.di

import com.jake.bucketplace.snsapp.auth.AuthManager
import com.jake.bucketplace.snsapp.auth.AuthManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AuthModule {
    @Singleton
    @Binds
    fun bindAuthManager(impl: AuthManagerImpl): AuthManager
}