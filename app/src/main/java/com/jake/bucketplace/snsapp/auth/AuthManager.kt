package com.jake.bucketplace.snsapp.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jake.bucketplace.snsapp.domain.model.User
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

abstract class AuthManager {
    protected val _isSignIn = MutableLiveData<Boolean>(false)
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    abstract fun signUp(nickName: String, introduction: String, password: String): Completable
    abstract fun signIn(nickName: String, password: String): Completable
    abstract fun signOut(): Completable
}