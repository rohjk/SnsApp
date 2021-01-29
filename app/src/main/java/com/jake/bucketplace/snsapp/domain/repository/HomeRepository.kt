package com.jake.bucketplace.snsapp.domain.repository

import com.jake.bucketplace.snsapp.domain.Home
import io.reactivex.Flowable

interface HomeRepository {
    fun getHome(): Flowable<Home>
}