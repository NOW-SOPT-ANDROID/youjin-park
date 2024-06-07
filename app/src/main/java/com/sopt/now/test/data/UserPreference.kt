package com.sopt.now.test.data

import android.content.Context

class UserPreference(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)

    // 사용자 아이디 저장
    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString("userId", userId).apply()
    }

    // 사용자 데이터 가져오기
    fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }
}