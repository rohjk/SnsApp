package com.jake.bucketplace.snsapp.data.model.mapper

import com.jake.bucketplace.snsapp.data.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {
    fun transform(user: User): com.jake.bucketplace.snsapp.domain.model.User =
        with(user) {
            return com.jake.bucketplace.snsapp.domain.model.User(
                id = id,
                nickName = nickName,
                introduction = introduction
            )
        }
}