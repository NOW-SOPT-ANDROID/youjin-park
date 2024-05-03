package com.sopt.now.compose.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseState(
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("message")
    val message: String
)