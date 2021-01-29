package com.jake.bucketplace.snsapp.di

import android.content.Context
import com.jake.bucketplace.snsapp.home.HomeComponent
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
}