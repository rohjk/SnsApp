package com.jake.bucketplace.snsapp.userdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jake.bucketplace.snsapp.BaseViewModel
import com.jake.bucketplace.snsapp.di.FragmentScope
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.model.User
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FragmentScope
class UserDetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @MainScheduler private val scheduler: Scheduler,
    private val disposable: CompositeDisposable
) : BaseViewModel() {

    companion object {
        private const val TAG = "UserDetailViewModel"
    }

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    override fun refresh() {
        val userId = _user.value?.id ?: -1
        loadUser(userId)
    }

    fun setUserId(id: Long) {
        loadUser(id)
    }

    private fun loadUser(id: Long) {
        _isLoading.value = true
        disposable.add(
            userRepository.getUser(id).observeOn(scheduler).doFinally {
                _isLoading.value = false
            }.subscribe({
                _user.value = it
            }, { error ->
                Log.e(TAG, "failure to get user : ${error.message}")
                _onError.value = error.message
            })
        )
    }

}