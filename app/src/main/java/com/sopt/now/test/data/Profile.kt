package com.sopt.now.test.data

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val userImage: String,
    val userName: String,
    val userInfo: String
)