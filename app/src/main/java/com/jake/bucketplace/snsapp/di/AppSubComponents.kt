package com.jake.bucketplace.snsapp.di

import com.jake.bucketplace.snsapp.home.HomeComponent
import com.jake.bucketplace.snsapp.photofeed.PhotoFeedComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class,
        PhotoFeedComponent::class
    ]
)
class AppSubcomponents
