package com.jake.bucketplace.snsapp.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jake.bucketplace.snsapp.di.MainScheduler
import com.jake.bucketplace.snsapp.domain.model.User
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManagerImpl @Inject constructor(
    private val auth: Auth,
    private val userRepository: UserRepository,
    @MainScheduler private val scheduler: Scheduler
): AuthManager() {

    override fun signUp(nickName: String, introduction: String, password: String): Completable {
        return userRepository.singUp(nickName, introduction, password).observeOn(scheduler).flatMapCompletable { userId ->
            auth.userId = userId
            updateStatus()
            Completable.complete()
        }.observeOn(scheduler)
    }

    override fun signIn(nickName: String, password: String): Completable {
        return userRepository.singIn(nickName, password).flatMapCompletable { userId ->
            auth.userId = userId
            updateStatus()
            Completable.complete()
        }.observeOn(scheduler)
    }

    override fun signOut(): Completable {
        auth.clear()
        updateStatus()
        return Completable.complete()
    }

    private fun updateStatus() {
        _isSignIn.value = auth.isSignin
    }

}