package com.jake.bucketplace.snsapp.data.repository

import com.jake.bucketplace.snsapp.data.model.mapper.CardMapper
import com.jake.bucketplace.snsapp.data.model.mapper.UserMapper
import com.jake.bucketplace.snsapp.domain.model.Home
import javax.inject.Inject

class HomeMapper @Inject constructor(
    private val cardMapper: CardMapper,
    private val userMapper: UserMapper
) {
    fun transform(homeResponse: HomeResponse): Home =
        with(homeResponse) {
            return Home(
                popularCards = homeResponse.popularCards.map {
                    cardMapper.transform(it)
                },
                papularUsers = homeResponse.popularUsers.map {
                    userMapper.transform(it)
                }
            )
        }
}