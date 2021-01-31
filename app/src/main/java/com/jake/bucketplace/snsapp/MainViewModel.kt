package com.jake.bucketplace.snsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.auth.AuthManager
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authManager: AuthManager
): ViewModel() {

    val isSignIn: LiveData<Boolean> by lazy {
        authManager.isSignIn
    }

    fun signOut() {
        authManager.signOut()
    }

}