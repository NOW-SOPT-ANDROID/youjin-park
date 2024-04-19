package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userId = intent.getStringExtra("userId")?: ""
                    val userPw = intent.getStringExtra("userPw")?: ""
                    val userName = intent.getStringExtra("userName")?: ""
                    val userMbti = intent.getStringExtra("userMbti")?: ""

                    MainCompose(userId, userPw, userName, userMbti)
                }
            }
        }
    }
}

@Composable
fun MainCompose(userId: String, userPw: String, userName: String, userMbti: String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Row (
            modifier = Modifier
            .fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.img_profile),
                contentDescription = "Profile",
                Modifier
                    .size(100.dp)
                    .aspectRatio(1f/1f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = userName,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "ID",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = userId,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "PW",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = userPw,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "MBTI",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = userMbti,
            fontSize = 20.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    NOWSOPTAndroidTheme {
        MainCompose("nowSopt", "password123", "UserName", "ISTP")
    }
}