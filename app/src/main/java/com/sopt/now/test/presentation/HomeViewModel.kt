package com.sopt.now.test.presentation

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.test.data.Friend

class HomeViewModel() : ViewModel() {
    val mockFriendList = mutableListOf<Friend>(
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "AAA",
            phone = "AAA님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "BBB",
            phone = "BBB님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "CCC",
            phone = "CCC님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "DDD",
            phone = "DDD님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "EEE",
            phone = "EEE님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "FFF",
            phone = "FFF님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "GGG",
            phone = "GGG님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "HHH",
            phone = "HHH님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "III",
            phone = "III님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "JJJ",
            phone = "JJJ님의 한 줄 소개",
        )
    )
}