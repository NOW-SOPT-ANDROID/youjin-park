package com.sopt.now.compose.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun MyPageView(userId: String, userPw: String, userPhone: String) {
    Log.d("myPage", "${userId}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 60.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.iv_user_profile),
                contentDescription = stringResource(id = R.string.app_name),
                Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = userPhone,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        UserInfoItem(
            label = stringResource(id = R.string.tv_id),
            value = userId
        )
        Spacer(modifier = Modifier.height(30.dp))
        UserInfoItem(
            label = stringResource(id = R.string.tv_pw),
            value = userPw
        )
        Spacer(modifier = Modifier.height(30.dp))
        UserInfoItem(
            label = stringResource(id = R.string.tv_user_description),
            value = userPhone
        )
    }
}


@Composable
fun UserInfoItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = value,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    NOWSOPTAndroidTheme {
        MyPageView("myPageSopt", "mypagePassword123", "MyPageUserName")
    }
}