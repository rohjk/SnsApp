package com.jake.bucketplace.snsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    protected val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    protected val _onError = MutableLiveData<String>()
    val onError: LiveData<String>
        get() = _onError

    abstract fun refresh()
}