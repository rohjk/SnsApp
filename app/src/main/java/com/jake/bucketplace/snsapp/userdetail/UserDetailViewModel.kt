package com.jake.bucketplace.snsapp.userdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @MainScheduler private val scheduler: Scheduler
): ViewModel() {

    companion object {
        private const val TAG = "UserDetailViewModel"
    }

    private val dispose = CompositeDisposable()

    fun setUserId(id: Long) {
        dispose.add(
            userRepository.getUser(id).observeOn(scheduler).subscribe({
                Log.d(TAG, "success to get user")
            },{
                Log.d(TAG, "failure to get user : ${it.message}")
            })
        )
    }

}