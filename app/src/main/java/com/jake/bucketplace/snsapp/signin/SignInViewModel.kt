package com.jake.bucketplace.snsapp.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.auth.AuthManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SignInViewModel @Inject constructor(
        private val authManager: AuthManager,
        private val disposable: CompositeDisposable
) : ViewModel() {

    companion object {
        private const val TAG = "SignUpViewModel"
    }

    val isSignIn: LiveData<Boolean> = authManager.isSignIn


    fun signIn(nickName: String, password: String) {
        disposable.add(
                authManager.signIn(nickName, password).subscribe({
                    Log.d(TAG, "Success to Sing In")
                },{
                    Log.e(TAG, "Failure to Sing In : ${it.message}")
                })
        )
    }
}