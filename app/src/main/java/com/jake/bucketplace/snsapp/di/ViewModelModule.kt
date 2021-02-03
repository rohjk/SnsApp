package com.jake.bucketplace.snsapp.di

import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.MainViewModel
import com.jake.bucketplace.snsapp.carddetail.CardDetailViewModel
import com.jake.bucketplace.snsapp.home.HomeViewModel
import com.jake.bucketplace.snsapp.photofeed.PhotoFeedViewModel
import com.jake.bucketplace.snsapp.signin.SignInViewModel
import com.jake.bucketplace.snsapp.signup.SignUpViewModel
import com.jake.bucketplace.snsapp.userdetail.UserDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(vm: MainViewModel) : ViewModel
}