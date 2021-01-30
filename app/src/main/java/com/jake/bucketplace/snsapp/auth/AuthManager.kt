package com.jake.bucketplace.snsapp.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jake.bucketplace.snsapp.domain.model.User
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManager @Inject constructor(
    private val auth: Auth,
    private val userRepository: UserRepository
) {
    private val _isSignIn = MutableLiveData<Boolean>(false)
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    fun signIn(nickName: String, password: String): Completable {
        auth.user = User(
            id = 123,
            nickName = "jake",
            introduction = "abc"
        )
        updateStatus()
        return Completable.complete()
    }

    fun signOut(): Completable {
        auth.user = null
        updateStatus()
        return Completable.complete()
    }

    private fun updateStatus() {
        _isSignIn.value = auth.isSignin
    }

}