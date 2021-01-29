package com.jake.bucketplace.snsapp.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.model.Card
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

    private val _popularCards = MutableLiveData<List<Card>>()
    val popularCards: LiveData<List<Card>>
        get() = _popularCards

    private val _popularUsers = MutableLiveData<List<User>>()
    val popularUsers: LiveData<List<User>>
        get() = _popularUsers

    init {
        dispose.add(
            homeRepository.getHome().observeOn(schedulers).subscribe({ home ->
                _popularCards.value = home.popularCards
                _popularUsers.value = home.papularUsers
            }, { error ->
                Log.e(TAG, "Failure to get Home : ${error.message}")
            })
        )
    }


}