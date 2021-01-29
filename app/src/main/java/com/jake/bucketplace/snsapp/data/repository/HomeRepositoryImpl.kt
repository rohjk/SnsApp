package com.jake.bucketplace.snsapp.data.repository

import com.jake.bucketplace.snsapp.data.network.HomeServiceApi
import com.jake.bucketplace.snsapp.domain.model.Home
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeServiceApi: HomeServiceApi,
    private val homeMapper: HomeMapper
) : HomeRepository {

    override fun getHome(): Single<Home> {
        return homeServiceApi.getHome().subscribeOn(Schedulers.io()).flatMap { response ->
            val homeResponse = response.body()
            if ( response.isSuccessful &&  homeResponse != null && homeResponse.status) {
                val home = homeMapper.transform(homeResponse)
                Single.just(home)
            } else {
                Single.error(Throwable("Failure to get Home"))
            }
        }
    }

}