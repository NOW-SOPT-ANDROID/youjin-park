package com.sopt.now.test.presentation

import androidx.lifecycle.ViewModel
import com.sopt.now.R

class HomeViewModel() : ViewModel() {
    val mockFriendList = mutableListOf<Friend>(
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "AAA",
            selfDescription = "AAA님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "BBB",
            selfDescription = "BBB님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "CCC",
            selfDescription = "CCC님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "DDD",
            selfDescription = "DDD님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "EEE",
            selfDescription = "EEE님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "FFF",
            selfDescription = "FFF님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "GGG",
            selfDescription = "GGG님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "HHH",
            selfDescription = "HHH님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "III",
            selfDescription = "III님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "JJJ",
            selfDescription = "JJJ님의 한 줄 소개",
        )
    )
}