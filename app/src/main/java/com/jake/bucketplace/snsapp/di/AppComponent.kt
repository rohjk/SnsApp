package com.jake.bucketplace.snsapp.di

import android.content.Context
import com.jake.bucketplace.snsapp.carddetail.CardDetailComponent
import com.jake.bucketplace.snsapp.home.HomeComponent
import com.jake.bucketplace.snsapp.photofeed.PhotoFeedComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [NetworkModule::class, SchedulerModule::class, RepositoryBindModule::class, AppSubcomponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun homeComponent(): HomeComponent.Factory
    fun photoFeedComponent(): PhotoFeedComponent.Factory
    fun cardDeatilComponent(): CardDetailComponent.Factory
}