package com.jake.bucketplace.snsapp.di

import com.jake.bucketplace.snsapp.home.HomeComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class
    ]
)
class AppSubcomponents
