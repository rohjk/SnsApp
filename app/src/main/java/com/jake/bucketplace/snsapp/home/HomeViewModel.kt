package com.jake.bucketplace.snsapp.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jake.bucketplace.snsapp.BaseViewModel
import com.jake.bucketplace.snsapp.di.FragmentScope
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.model.Home
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FragmentScope
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    @MainScheduler private val schedulers: Scheduler,
    private val disposable: CompositeDisposable
) : BaseViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _home = MutableLiveData<Home>()
    val home: LiveData<Home>
        get() = _home

    init {
        loadHome()
    }

    override fun refresh() {
        loadHome()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    private fun loadHome() {
        _isLoading.value = true
        disposable.add(
            homeRepository.getHome().observeOn(schedulers).doFinally {
                _isLoading.value = false
            }.subscribe({ item ->
               _home.value = item
            }, { error ->
                Log.e(TAG, "Failure to get Home : ${error.message}")
                _onError.value = error.message
            })
        )
    }


}