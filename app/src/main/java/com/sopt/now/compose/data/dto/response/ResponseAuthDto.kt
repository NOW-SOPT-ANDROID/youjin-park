package com.sopt.now.compose.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAuthDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)