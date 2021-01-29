package com.jake.bucketplace.snsapp.domain.repository

import com.jake.bucketplace.snsapp.domain.model.Home
import io.reactivex.Single

interface HomeRepository {
    fun getHome(): Single<Home>
}