package com.jake.bucketplace.snsapp.di

import com.jake.bucketplace.snsapp.data.repository.card.CardRepositoryImpl
import com.jake.bucketplace.snsapp.data.repository.home.HomeRepositoryImpl
import com.jake.bucketplace.snsapp.domain.repository.CardRepository
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBindModule {

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindCardRepository(impl: CardRepositoryImpl): CardRepository
}