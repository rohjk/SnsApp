package com.jake.bucketplace.snsapp.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.BaseViewModel
import com.jake.bucketplace.snsapp.auth.AuthManager
import com.jake.bucketplace.snsapp.di.FragmentScope
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FragmentScope
class SignInViewModel @Inject constructor(
        private val authManager: AuthManager,
        private val disposable: CompositeDisposable
) : BaseViewModel() {

    companion object {
        private const val TAG = "SignUpViewModel"
    }

    val isSignIn: LiveData<Boolean> by lazy {
        authManager.isSignIn
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun signIn(nickName: String, password: String) {
        val isRunning = _isLoading.value ?: false
        if (!isRunning) {
            requestSingin(nickName, password)
        }
    }

    private fun requestSingin(nickName: String, password: String) {
        _isLoading.value = true
        disposable.add(
            authManager.signIn(nickName, password).doFinally {
                _isLoading.value = false
            }.subscribe({
                Log.d(TAG, "Success to Sing In")
            },{ error ->
                Log.e(TAG, "Failure to Sing In : ${error.message}")
                _onError.value = error.message
            })
        )
    }

    override fun refresh() {
        throw Throwable("Not_implemented")
    }
}