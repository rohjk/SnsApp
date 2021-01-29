package com.jake.bucketplace.snsapp.data.repository

import com.jake.bucketplace.snsapp.data.network.HomeServiceApi
import com.jake.bucketplace.snsapp.domain.model.Home
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import io.reactivex.Flowable
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeServiceApi: HomeServiceApi
) : HomeRepository {

    override fun getHome(): Flowable<Home> {
        TODO("Not yet implemented")
    }

}