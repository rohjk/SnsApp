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
    @ViewModelKey(PhotoFeedViewModel::class)
    abstract fun bindPhotoFeedViewModel(vm: PhotoFeedViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(vm: SignInViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(vm: SignUpViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    abstract fun bindUserDetailViewModel(vm: UserDetailViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(vm: MainViewModel) : ViewModel
}