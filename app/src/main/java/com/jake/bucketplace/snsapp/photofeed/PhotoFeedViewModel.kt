package com.jake.bucketplace.snsapp.photofeed

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.repository.CardRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PhotoFeedViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    @MainScheduler private val scheduler: Scheduler
): ViewModel() {

    private val dispose = CompositeDisposable()

    companion object {
        private const val TAG = "PhotoFeedViewModel"
    }

    init {
        loadCard(true)
    }

    override fun onCleared() {
        dispose.clear()
        super.onCleared()
    }

    private fun loadCard(forceUpdate: Boolean) {
        dispose.add(
            cardRepository.getCards(forceUpdate).observeOn(scheduler).subscribe({ items ->
                Log.d(TAG, "success to get cards ${items.size}")
            },{
                Log.e(TAG, "Failure to get cards ${it.message}")
            })
        )
    }



}