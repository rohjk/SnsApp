package com.jake.bucketplace.snsapp.domain.repository

import com.jake.bucketplace.snsapp.domain.model.User
import io.reactivex.Single

interface UserRepository {
    fun getUser(id: Long): Single<User>
    fun signUp(nickName: String, introduction: String, password: String): Single<Long>
    fun signIn(nickName: String, password: String): Single<Long>
}