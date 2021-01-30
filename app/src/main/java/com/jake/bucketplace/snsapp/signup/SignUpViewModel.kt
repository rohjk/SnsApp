package com.jake.bucketplace.snsapp.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.auth.AuthManager
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val authManager: AuthManager,
    private val userRepository: UserRepository
): ViewModel() {

    val isSignIn: LiveData<Boolean> = authManager.isSignIn

    fun signUp(nickName: String, introduction: String, password: String) {
        authManager.signIn(nickName, password)
    }

}