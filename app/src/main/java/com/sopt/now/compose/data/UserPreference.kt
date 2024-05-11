package com.sopt.now.compose.data

import android.content.Context

import androidx.core.content.edit

class UserPreference(context: Context) {
    private val sharedPreferences = context.applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)

    // 사용자 아이디 저장
    fun saveUserId(userId: String) {
        sharedPreferences.edit {
            putString("userId", userId)
        }
    }

    // 사용자 아이디 가져오기
    fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }

    // 사용자 데이터 저장
    fun saveUserData(userData: UserData) {
        sharedPreferences.edit {
            putString("userId", userData.userId)
            putString("userName", userData.userName)
            putString("userPhone", userData.userPhone)
        }
    }

    // 사용자 데이터 가져오기
    fun getUserData(): UserData? {
        with(sharedPreferences){
            val userId = getString("userId", null)
            val userName = getString("userName", null)
            val userPhone = getString("userPhone", null)

            return if (userId != null && userName != null && userPhone != null) {
                UserData(userId, userName, userPhone)
            } else {
                null
            }
        }
    }
}