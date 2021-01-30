package com.jake.bucketplace.snsapp.carddetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.model.CardDetail
import com.jake.bucketplace.snsapp.domain.repository.CardRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CardDetailViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    @MainScheduler private val scheduler: Scheduler
): ViewModel(){

    companion object {
        private const val TAG = "CardDetailViewModel"
    }

    private val dispose = CompositeDisposable()

    private val _cardDetail = MutableLiveData<CardDetail>()
    val cardDetail: LiveData<CardDetail>
        get() = _cardDetail

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun setCardId(id: Long) {
       loadCardDeatil(id)
    }

    fun refresh() {
        val id = _cardDetail.value?.card?.id ?: -1
        loadCardDeatil(id)
    }

    private fun loadCardDeatil(id: Long) {
        _isLoading.value = true
        dispose.add(
            cardRepository.getCard(id).observeOn(scheduler).doFinally {
                _isLoading.value = false
            }.subscribe({
                _cardDetail.value = it
            },{
                Log.d(TAG, "Failure to get Card Detail : ${it.message}")
            })
        )
    }

    override fun onCleared() {
        dispose.clear()
        super.onCleared()
    }

}