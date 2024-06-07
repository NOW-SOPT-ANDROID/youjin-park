package com.sopt.now.test.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    val userId: String,
    val userName: String,
    val userPhone: String
): Parcelable