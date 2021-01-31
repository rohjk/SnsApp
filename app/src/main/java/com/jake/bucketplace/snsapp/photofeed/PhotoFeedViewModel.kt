package com.jake.bucketplace.snsapp.photofeed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.BaseViewModel
import com.jake.bucketplace.snsapp.di.FragmentScope
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.model.Card
import com.jake.bucketplace.snsapp.domain.repository.CardRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FragmentScope
class PhotoFeedViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    @MainScheduler private val scheduler: Scheduler,
    private val disposable: CompositeDisposable
) : BaseViewModel() {

    companion object {
        private const val TAG = "PhotoFeedViewModel"
    }

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>>
        get() = _cards

    init {
        loadCard(true)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    fun loadMore() {
        val loading = _isLoading.value ?: false
        if(!loading) {
            loadCard(false)
        }
    }

    override fun refresh() {
        loadCard(true)
    }

    private fun loadCard(forceUpdate: Boolean) {
        _isLoading.value = true
        disposable.add(
            cardRepository.getCards(forceUpdate).observeOn(scheduler).doFinally {
                _isLoading.value = false
            }.subscribe({ cards ->
                val items: MutableList<Card> = mutableListOf()
                if (!forceUpdate) {
                    val preCards = _cards.value ?: emptyList()
                    items.addAll(preCards)
                }
                items.addAll(cards)
                _cards.value = items
            }, { error ->
                Log.e(TAG, "Failure to get cards ${error.message}")
                _onError.value = error.message
            })
        )
    }


}