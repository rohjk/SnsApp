package com.jake.bucketplace.snsapp.auth

import com.jake.bucketplace.snsapp.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Auth @Inject constructor() {
    var user: User? = null
    var isSignin: Boolean = false
        get() = user != null
}