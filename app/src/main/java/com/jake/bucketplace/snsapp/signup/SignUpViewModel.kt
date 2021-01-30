package com.jake.bucketplace.snsapp.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.auth.AuthManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
        private val authManager: AuthManager,
        private val disposable: CompositeDisposable
) : ViewModel() {

    companion object {
        private const val TAG = "SignUpViewModel"
    }

    val isSignIn: LiveData<Boolean> = authManager.isSignIn


    fun signUp(nickName: String, introduction: String, password: String) {
        disposable.add(
                authManager.signUp(nickName, introduction, password).subscribe({
                    Log.d(TAG, "Success to Sing Up")
                },{
                    Log.e(TAG, "Failure to Sing Up : ${it.message}")
                })
        )
    }
}