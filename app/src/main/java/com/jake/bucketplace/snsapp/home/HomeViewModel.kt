package com.jake.bucketplace.snsapp.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.model.Card
import com.jake.bucketplace.snsapp.domain.model.Home
import com.jake.bucketplace.snsapp.domain.model.User
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    @MainScheduler private val schedulers: Scheduler
) : ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val dispose = CompositeDisposable()

    private val _home = MutableLiveData<Home>()
    val home: LiveData<Home>
        get() = _home

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        loadHome()
    }

    fun refresh() {
        loadHome()
    }

    private fun loadHome() {
        _isLoading.value = true
        dispose.add(
            homeRepository.getHome().observeOn(schedulers).doFinally {
                _isLoading.value = false
            }.subscribe({ item ->
               _home.value = item
            }, { error ->
                Log.e(TAG, "Failure to get Home : ${error.message}")
            })
        )
    }


}