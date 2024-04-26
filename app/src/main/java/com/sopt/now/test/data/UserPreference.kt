package com.sopt.now.test.data

import android.content.Context
import com.sopt.now.R

class UserPreference(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)

    // 사용자 데이터 저장
    fun saveUserData(userData: UserData) {
        with(sharedPreferences.edit()){
            putString("userId", userData.userId)
            putString("userPw", userData.userPw)
            putString("userName", userData.userName)
            putString("selfDescription", userData.selfDescription)
            apply()
        }
        setFriendList(userData)
    }

    // 사용자 데이터 가져오기
    fun getUserData(): UserData? {
        with(sharedPreferences){
            val userId = getString("userId", null)
            val userPw = getString("userPw", null)
            val userName = getString("userName", null)
            val selfDescription = getString("selfDescription", null)

            return if (userId != null && userPw != null && userName != null && selfDescription != null) {
                UserData(userId, userPw, userName, selfDescription)
            } else {
                null
            }
        }
    }

    // 리스트에 사용자 정보 추가
    private fun setFriendList(userData: UserData){
        val newFriend = Friend(
            profileImage = R.drawable.iv_user_profile,
            name = userData.userName,
            selfDescription = userData.selfDescription
        )
        mockFriendList.add(0, newFriend)
    }
}