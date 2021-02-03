package com.jake.bucketplace.snsapp.di

import android.content.Context
import com.jake.bucketplace.snsapp.MainActivity
import com.jake.bucketplace.snsapp.carddetail.di.CardDetailComponent
import com.jake.bucketplace.snsapp.home.di.HomeComponent
import com.jake.bucketplace.snsapp.photofeed.PhotoFeedComponent
import com.jake.bucketplace.snsapp.signin.SignInComponent
import com.jake.bucketplace.snsapp.signup.SignUpComponent
import com.jake.bucketplace.snsapp.userdetail.UserDetailComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        SchedulerModule::class,
        RxModule::class,
        RepositoryModule::class,
        AuthModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        AppSubcomponents::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity): MainActivity
    fun homeComponent(): HomeComponent.Factory
    fun photoFeedComponent(): PhotoFeedComponent.Factory
    fun cardDeatilComponent(): CardDetailComponent.Factory
    fun userDeatilComponent(): UserDetailComponent.Factory
    fun signUpComponent(): SignUpComponent.Factory
    fun signInComponent(): SignInComponent.Factory
}