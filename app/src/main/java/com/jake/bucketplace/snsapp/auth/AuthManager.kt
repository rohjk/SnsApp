package com.jake.bucketplace.snsapp.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable

abstract class AuthManager {
    protected val _isSignIn = MutableLiveData<Boolean>(false)
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    abstract fun signUp(nickName: String, introduction: String, password: String): Completable
    abstract fun signIn(nickName: String, password: String): Completable
    abstract fun signOut(): Completable
}