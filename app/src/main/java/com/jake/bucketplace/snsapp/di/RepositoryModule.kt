package com.jake.bucketplace.snsapp.di

import com.jake.bucketplace.snsapp.data.repository.card.CardRepositoryImpl
import com.jake.bucketplace.snsapp.data.repository.home.HomeRepositoryImpl
import com.jake.bucketplace.snsapp.data.repository.user.UserRepositoryImpl
import com.jake.bucketplace.snsapp.domain.repository.CardRepository
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindCardRepository(impl: CardRepositoryImpl): CardRepository

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}