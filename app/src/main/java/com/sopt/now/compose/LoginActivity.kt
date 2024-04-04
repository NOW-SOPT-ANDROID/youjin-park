package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun LoginCompose(){
    var userId by remember { mutableStateOf("") }
    var userPw by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp),
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Welcom To SOPT",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "ID",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = userId,
            onValueChange = { userId = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("아이디를 입력하세요.") },
            singleLine = true, //단일 줄로 제한
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "비밀번호",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = userPw,
            onValueChange = { userPw = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("비밀번호를 입력하세요.") },
            singleLine = true, //단일 줄로 제한
            visualTransformation = PasswordVisualTransformation(), // 비밀번호 마스킹 처리
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = { /* 클릭 시 수행될 동작 */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("로그인", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /* 클릭 시 수행될 동작 */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("회원가입", color = Color.White)
        }
        Spacer(modifier = Modifier.padding(bottom = 60.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    NOWSOPTAndroidTheme {
        LoginCompose()
    }
}