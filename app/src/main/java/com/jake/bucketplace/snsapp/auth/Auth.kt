package com.jake.bucketplace.snsapp.auth

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Auth @Inject constructor() {

    companion object {
        const val SIGN_OUT_USER_ID: Long = -1
    }

    var userId: Long = SIGN_OUT_USER_ID
    var isSignin: Boolean = false
        get() = userId != SIGN_OUT_USER_ID

    fun clear() {
        userId = SIGN_OUT_USER_ID
    }
}