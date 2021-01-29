package com.jake.bucketplace.snsapp.data.repository

import com.jake.bucketplace.snsapp.domain.Home
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import io.reactivex.Flowable

class HomeRepositoryImpl: HomeRepository {

    override fun getHome(): Flowable<Home> {
        TODO("Not yet implemented")
    }

}