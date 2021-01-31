package com.jake.bucketplace.snsapp.data.repository.user

import com.jake.bucketplace.snsapp.data.model.mapper.UserMapper
import com.jake.bucketplace.snsapp.data.network.UserServiceApi
import com.jake.bucketplace.snsapp.di.IOScheduler
import com.jake.bucketplace.snsapp.domain.model.User
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userServiceApi: UserServiceApi,
    @IOScheduler private val scheduler: Scheduler,
    private val userMapper: UserMapper
): UserRepository {

    override fun getUser(id: Long): Single<User> {
        return userServiceApi.getUser(id).subscribeOn(scheduler).flatMap { response ->
            val userResponse = response.body()
            if (response.isSuccessful && userResponse!= null) {
                if (userResponse.status) {
                    val user = userMapper.transform(userResponse.user)
                    Single.just(user)
                } else {
                    Single.error(Throwable(userResponse.errorMessage))
                }
            } else {
                Single.error(Throwable("FAILURE_TO_GET_USER_${response.code()}"))
            }
        }
    }

    override fun singUp(nickName: String, introduction: String, password: String): Single<Long> {
        return userServiceApi.signUp(nickName, introduction, password).subscribeOn(scheduler).flatMap { response ->
            val authResponse = response.body()
            if (response.isSuccessful && authResponse != null ) {
                if (authResponse.status) {
                    Single.just(authResponse.userId)

                } else {
                    Single.error(Throwable(authResponse.errorMessage))
                }
            } else {
                Single.error(Throwable("FAILURE_TO_SING_UP_${response.code()}"))
            }
        }
    }

    override fun singIn(nickName: String, password: String): Single<Long> {
        return userServiceApi.signIn(nickName, password).subscribeOn(scheduler).flatMap { response ->
            val authResponse = response.body()
            if (response.isSuccessful && authResponse != null ) {
                if (authResponse.status) {
                    Single.just(authResponse.userId)
                } else {
                    Single.error(Throwable(authResponse.errorMessage))
                }
            } else {
                Single.error(Throwable("FAILURE_TO_SIGN_IN_${response.code()}"))
            }
        }
    }
}