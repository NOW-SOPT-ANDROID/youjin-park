package com.sopt.now.test.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    val userId: String,
    val userPw: String,
    val userName: String,
    val selfDescription: String
): Parcelable