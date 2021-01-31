package com.jake.bucketplace.snsapp.data.repository.home

import com.jake.bucketplace.snsapp.data.network.HomeServiceApi
import com.jake.bucketplace.snsapp.data.repository.home.HomeMapper
import com.jake.bucketplace.snsapp.di.IOScheduler
import com.jake.bucketplace.snsapp.domain.model.Home
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeServiceApi: HomeServiceApi,
    @IOScheduler private val schedulers: Scheduler,
    private val homeMapper: HomeMapper
) : HomeRepository {

    override fun getHome(): Single<Home> {
        return homeServiceApi.getHome().subscribeOn(schedulers).flatMap { response ->
            val homeResponse = response.body()
            if ( response.isSuccessful &&  homeResponse != null) {
                if (homeResponse.status) {
                    val home = homeMapper.transform(homeResponse)
                    Single.just(home)
                } else {
                    Single.error(Throwable(homeResponse.errorMessage))
                }
            } else {
                Single.error(Throwable("FAILURE_TO_GET_HOME_${response.code()}"))
            }
        }
    }

}