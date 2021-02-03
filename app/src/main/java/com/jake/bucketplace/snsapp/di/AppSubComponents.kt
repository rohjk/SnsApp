package com.jake.bucketplace.snsapp.di

import com.jake.bucketplace.snsapp.carddetail.di.CardDetailComponent
import com.jake.bucketplace.snsapp.home.di.HomeComponent
import com.jake.bucketplace.snsapp.photofeed.di.PhotoFeedComponent
import com.jake.bucketplace.snsapp.signin.SignInComponent
import com.jake.bucketplace.snsapp.signup.SignUpComponent
import com.jake.bucketplace.snsapp.userdetail.UserDetailComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class,
        PhotoFeedComponent::class,
        CardDetailComponent::class,
        UserDetailComponent::class,
        SignUpComponent::class,
        SignInComponent::class
    ]
)
class AppSubcomponents
