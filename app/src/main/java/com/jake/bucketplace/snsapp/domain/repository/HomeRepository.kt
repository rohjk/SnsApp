package com.jake.bucketplace.snsapp.domain.repository

import com.jake.bucketplace.snsapp.domain.model.Home
import io.reactivex.Flowable

interface HomeRepository {
    fun getHome(): Flowable<Home>
}