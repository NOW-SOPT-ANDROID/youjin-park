package com.sopt.now.compose.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val userId: String,
    val userName: String,
    val userPhone: String
)