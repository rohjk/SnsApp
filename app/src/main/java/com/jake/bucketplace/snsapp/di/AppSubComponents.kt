package com.jake.bucketplace.snsapp.di

import com.jake.bucketplace.snsapp.carddetail.CardDetailComponent
import com.jake.bucketplace.snsapp.carddetail.CardDetailFragment
import com.jake.bucketplace.snsapp.home.HomeComponent
import com.jake.bucketplace.snsapp.photofeed.PhotoFeedComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class,
        PhotoFeedComponent::class,
        CardDetailComponent::class
    ]
)
class AppSubcomponents
