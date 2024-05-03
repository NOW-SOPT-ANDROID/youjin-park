package com.sopt.now.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.sopt.now.compose.item.FriendItem
import com.sopt.now.compose.item.Profile
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.now.compose.item.UserItem
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun HomeView() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            UserItem(friendList.first())
        }

        // 첫 번째 요소는 건너뛰고 적용
        itemsIndexed(friendList) { index, profile ->
            if (index > 0) {
                FriendItem(profile)
            }
        }
    }
}

val friendList = listOf(
    Profile(
        userProfile = R.drawable.iv_user_profile,
        userName = "박유진",
        userDescription = "안녕하세요~!!"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "AAA",
        userDescription = "AAA님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "BBB",
        userDescription = "BBB님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "CCC",
        userDescription = "CCC님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "DDD",
        userDescription = "DDD님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "EEE",
        userDescription = "EEE님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "FFF",
        userDescription = "FFF님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "GGG",
        userDescription = "GGG님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "HHH",
        userDescription = "HHH님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "III",
        userDescription = "III님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "JJJ",
        userDescription = "JJJ님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "KKK",
        userDescription = "KKK님의 한 줄 소개"
    ),
    Profile(
        userProfile = R.drawable.iv_friend_profile,
        userName = "LLL",
        userDescription = "LLL님의 한 줄 소개"
    )
)

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NOWSOPTAndroidTheme {
        HomeView()
    }
}