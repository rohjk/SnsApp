package com.jake.bucketplace.snsapp.signup

import android.util.Log
import androidx.lifecycle.LiveData
import com.jake.bucketplace.snsapp.BaseViewModel
import com.jake.bucketplace.snsapp.auth.AuthManager
import com.jake.bucketplace.snsapp.di.FragmentScope
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
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

    fun signUp(nickName: String, introduction: String, password: String) {
        val isRunning = _isLoading.value ?: false
        if (!isRunning) {
            requestSingUp(nickName, introduction, password)
        }
    }

    private fun requestSingUp(nickName: String, introduction: String, password: String) {
        _isLoading.value = true
        disposable.add(
            authManager.signUp(nickName, introduction, password).doFinally {
                _isLoading.value = false
            }.subscribe({
                Log.d(TAG, "Success to Sing Up")
            }, { error ->
                Log.e(TAG, "Failure to Sing Up : ${error.message}")
                _onError.value = error.message
            })
        )
    }

    override fun refresh() {
        throw Throwable("Not_implemented")
    }
}