package com.sopt.now.compose.data

import androidx.annotation.DrawableRes

data class Friend(
    @DrawableRes val profileImage: Int,
    val name: String,
    val phone: String,
) {
    companion object {
        const val TYPE_USER = 0
        const val TYPE_FRIEND = 1
    }
}