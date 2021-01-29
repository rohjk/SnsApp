package com.jake.bucketplace.snsapp.domain.model

import com.jake.bucketplace.snsapp.domain.model.Card
import com.jake.bucketplace.snsapp.domain.model.User

data class Home (
    val popularCards: List<Card>,
    val papularUsers: List<User>
)