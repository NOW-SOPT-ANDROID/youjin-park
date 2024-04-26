package com.sopt.now.test.data

import android.content.Context
import androidx.lifecycle.ViewModel

class UserViewModel(context: Context) : ViewModel() {
    private val userPreference = UserPreference(context)

    // 사용자 데이터 설정
    fun setUserData(userData: UserData) {
        userPreference.saveUserData(userData)
    }

    // 사용자 데이터 가져오기
    fun getUserData(): UserData? {
        return userPreference.getUserData()
    }
}