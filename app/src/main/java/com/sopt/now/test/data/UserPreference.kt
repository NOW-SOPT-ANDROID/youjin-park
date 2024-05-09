package com.sopt.now.test.data

import android.content.Context

class UserPreference(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)

    // 사용자 아이디 저장
    fun saveUserId(userId: String) {
        with(sharedPreferences.edit()){
            putString("userId", userId)
            apply()
        }
    }

    // 사용자 데이터 가져오기
    fun getUserId(): String? {
        with(sharedPreferences){
            val userId = getString("userId", null)
            return userId
        }
    }

    // 사용자 데이터 저장
    fun saveUserData(userData: UserData) {
        with(sharedPreferences.edit()){
            putString("userId", userData.userId)
            putString("userName", userData.userName)
            putString("userPhone", userData.userPhone)
            apply()
        }
    }

    // 사용자 데이터 가져오기
    fun getUserData(): UserData? {
        with(sharedPreferences){
            val userId = getString("userId", null)
            val userName = getString("userName", null)
            val userPhone = getString("userPhone", null)

            // takeIf 사용
            return userId?.takeIf { userName != null && userPhone != null }?.let {
                UserData(userId, userName!!, userPhone!!)
            }
        }
    }
}