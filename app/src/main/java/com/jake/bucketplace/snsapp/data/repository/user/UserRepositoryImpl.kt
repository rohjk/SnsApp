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
            if (response.isSuccessful && userResponse!= null && userResponse.status) {
                val user = userMapper.transform(userResponse.user)
                Single.just(user)
            } else {
                Single.error(Throwable("Failure to get User"))
            }
        }
    }
}