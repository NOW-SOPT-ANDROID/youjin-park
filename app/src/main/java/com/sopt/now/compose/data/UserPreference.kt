package com.sopt.now.compose.data

import android.content.Context

import androidx.core.content.edit
import com.sopt.now.compose.data.UserPreference.UserPreferenceKeys.KEY_USER_ID
import com.sopt.now.compose.data.UserPreference.UserPreferenceKeys.KEY_USER_NAME
import com.sopt.now.compose.data.UserPreference.UserPreferenceKeys.KEY_USER_PHONE
import com.sopt.now.compose.data.UserPreference.UserPreferenceKeys.PREF_USER_DATA

class UserPreference(context: Context) {
    private val sharedPreferences = context.applicationContext.getSharedPreferences(PREF_USER_DATA, Context.MODE_PRIVATE)

    // 사용자 아이디 저장
    fun saveUserId(userId: String) {
        sharedPreferences.edit {
            putString(KEY_USER_ID, userId)
        }
    }

    // 사용자 아이디 가져오기
    fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    // 사용자 데이터 저장
    fun saveUserData(userData: UserData) {
        sharedPreferences.edit {
            putString(KEY_USER_ID, userData.userId)
            putString(KEY_USER_NAME, userData.userName)
            putString(KEY_USER_PHONE, userData.userPhone)
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
    object UserPreferenceKeys {
        const val PREF_USER_DATA = "userData"
        const val KEY_USER_ID = "userId"
        const val KEY_USER_NAME = "userName"
        const val KEY_USER_PHONE = "userPhone"
    }
}