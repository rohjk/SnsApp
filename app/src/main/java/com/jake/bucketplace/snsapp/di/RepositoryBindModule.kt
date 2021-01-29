package com.jake.bucketplace.snsapp.di

import com.jake.bucketplace.snsapp.data.repository.HomeRepositoryImpl
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBindModule {

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository
}