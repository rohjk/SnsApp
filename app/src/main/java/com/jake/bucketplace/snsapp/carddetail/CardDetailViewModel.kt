package com.jake.bucketplace.snsapp.carddetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jake.bucketplace.snsapp.BaseViewModel
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.model.CardDetail
import com.jake.bucketplace.snsapp.domain.repository.CardRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CardDetailViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    @MainScheduler private val scheduler: Scheduler,
    private val disposable: CompositeDisposable
) : BaseViewModel() {

    companion object {
        private const val TAG = "CardDetailViewModel"
    }

    private val _cardDetail = MutableLiveData<CardDetail>()
    val cardDetail: LiveData<CardDetail>
        get() = _cardDetail

    fun setCardId(id: Long) {
        loadCardDeatil(id)
    }

    override fun refresh() {
        val id = _cardDetail.value?.card?.id ?: -1
        loadCardDeatil(id)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    private fun loadCardDeatil(id: Long) {
        _isLoading.value = true
        disposable.add(
            cardRepository.getCard(id).observeOn(scheduler).doFinally {
                _isLoading.value = false
            }.subscribe({
                _cardDetail.value = it
            }, {
                Log.d(TAG, "Failure to get Card Detail : ${it.message}")
            })
        )
    }

}