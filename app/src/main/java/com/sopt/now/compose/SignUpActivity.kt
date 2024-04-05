package com.sopt.now.compose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpCompose()
                }
            }
        }
    }
}

@Composable
fun SignUpCompose(){
    val context = LocalContext.current
    var userId by remember { mutableStateOf("") }
    var userPw by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var userMbti by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Sign Up",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "ID",
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = userId,
            onValueChange = { userId = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("아이디를 입력하세요.") },
            singleLine = true //단일 줄로 제한
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "비밀번호",
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = userPw,
            onValueChange = { userPw = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("비밀번호를 입력하세요.") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "닉네임",
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = userName,
            onValueChange = { userName = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("닉네임을 입력하세요.") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "MBTI",
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = userMbti,
            onValueChange = { userMbti = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("MBTI를 입력하세요.") },
            singleLine = true
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = {
                checkSignUp(context, userId, userPw, userName, userMbti)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("회원가입", color = Color.White)
        }
        Spacer(modifier = Modifier.padding(bottom = 60.dp))
    }
}

// 회원 가입 가능 여부 체크
fun checkSignUp(context: Context, userId: String, userPw: String, userName: String, userMbti: String) {
    val isValidId = userId.length in 6..10
    val isValidPw = userPw.length in 8..12
    val isValidName = userName.trim().isEmpty() // 공백으로만 이루어진 경우 판단
    val isEmpty = userId.isEmpty() || userPw.isEmpty() || userName.isEmpty() || userMbti.isEmpty()

    val message = when {
        !isValidId -> "ID는 6~10 글자여야 합니다."
        !isValidPw -> "Password는 8~12 글자여야 합니다."
        isValidName -> "공백으로만 이루어진 닉네임은 불가합니다."
        isEmpty -> "모든 정보를 입력해주세요."
        else -> {
            "회원가입 성공!"
        }
    }
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    NOWSOPTAndroidTheme {
        SignUpCompose()
    }
}