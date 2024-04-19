package com.sopt.now.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
                    val userId = intent.getStringExtra("userId").toString()
                    val userPw = intent.getStringExtra("userPw").toString()
                    val userName = intent.getStringExtra("userName").toString()
                    val userDescription = intent.getStringExtra("userDescription").toString()

                    LoginCompose(userId, userPw, userName ,userDescription)
                }
            }
        }
    }
}

@Composable
fun LoginInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            singleLine = true,
            visualTransformation = if (label == "비밀번호") PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = if (label == "비밀번호") KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default
        )
    }
}

@Composable
fun LoginButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text, color = Color.White)
    }
}

@Composable
fun LoginCompose(userId: String, userPw: String, userName: String, userDescription: String) {
    val context = LocalContext.current
    var inputId by remember { mutableStateOf("") }
    var inputPw by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 60.dp),
    ) {
        Text(
            text = stringResource(id = R.string.title_login),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(60.dp))
        LoginInputField(label = stringResource(id = R.string.tv_id), value = inputId) { inputId = it }
        Spacer(modifier = Modifier.height(60.dp))
        LoginInputField(label = stringResource(id = R.string.tv_pw), value = inputPw) { inputPw = it }
        Spacer(modifier = Modifier.weight(2f))
        LoginButton(stringResource(id = R.string.btn_login)) {
            if (userId == inputId && userPw == inputPw) {
                moveToMain(context, userId, userPw, userName, userDescription)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LoginButton(stringResource(id = R.string.btn_sign_up)) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }
    }
}

private fun moveToMain(context: Context, userId: String, userPw: String, userName: String, userDescription: String) {
    val intent = Intent(context, MainActivity::class.java).apply {
        putExtra("userId", userId)
        putExtra("userPw", userPw)
        putExtra("userName", userName)
        putExtra("userDescription", userDescription)
    }
    context.startActivity(intent)
    Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    NOWSOPTAndroidTheme {
        LoginCompose("","","","")
    }
}