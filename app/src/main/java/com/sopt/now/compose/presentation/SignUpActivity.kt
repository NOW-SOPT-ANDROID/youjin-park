package com.sopt.now.compose.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.data.ApiFactory
import com.sopt.now.compose.data.ApiFactory.userPreference
import com.sopt.now.compose.data.UserPreference
import com.sopt.now.compose.data.dto.request.RequestSignUpDto
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
                    userPreference = UserPreference(this)
                    ApiFactory.initializeUserPreference(userPreference)

                    val viewModel: SignUpViewModel = remember { SignUpViewModel() }
                    SignUpCompose(viewModel)
                }
            }
        }
    }
}

@Composable
fun SignUpCompose(viewModel: SignUpViewModel){
    val context = LocalContext.current
    var userId by remember { mutableStateOf("") }
    var userPw by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var userDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 60.dp),
    ) {
        Text(
            text = stringResource(id = R.string.title_sign_up),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(60.dp))
        TextFieldWithLabel(
            stringResource(id = R.string.tv_id), userId,
            stringResource(id = R.string.hint_id)
        ) { userId = it }
        Spacer(modifier = Modifier.height(30.dp))
        TextFieldWithLabel(
            stringResource(id = R.string.tv_pw), userPw,
            stringResource(id = R.string.hint_pw)
        ) { userPw = it }
        Spacer(modifier = Modifier.height(30.dp))
        TextFieldWithLabel(
            stringResource(id = R.string.tv_user_name), userName,
            stringResource(id = R.string.hint_user_name)
        ) { userName = it }
        Spacer(modifier = Modifier.height(30.dp))
        TextFieldWithLabel(
            stringResource(id = R.string.tv_user_description), userDescription,
            stringResource(id = R.string.hint_user_description)
        ) { userDescription = it }
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = {
                checkSignUp(viewModel, context, userId, userPw, userName, userDescription)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.btn_sign_up), color = Color.White)
        }
    }
}

@Composable
fun TextFieldWithLabel(label: String, value: String, hint: String, onValueChange: (String) -> Unit) {
    Column {
        Text(
            text = label,
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(hint) },
            singleLine = true
        )
    }
}

// 회원 가입 가능 여부 체크
fun checkSignUp(
    viewModel: SignUpViewModel,
    context: Context,
    userId: String,
    userPw: String,
    userName: String,
    userDescription: String) {
    val isValidPw = userPw.length in 8..12
    val isValidName = userName.trim().isEmpty() // 공백으로만 이루어진 경우 판단

    val message = when {
        !isValidPw -> context.getString(R.string.error_invalid_pw)
        isValidName -> context.getString(R.string.error_empty_name)
        else -> {
            viewModel.signUp(RequestSignUpDto(userId, userPw, userName, userDescription))
            moveToLogin(context, userId, userPw, userName, userDescription)
            context.getString(R.string.signup_success)
        }
    }
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

// Move to login page
private fun moveToLogin(context: Context, userId: String, userPw: String, userName: String, userDescription: String) {
    val intent = Intent(context, LoginActivity::class.java).apply {
        putExtra("userId", userId)
        putExtra("userPw", userPw)
        putExtra("userName", userName)
        putExtra("userDescription", userDescription)
    }
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    NOWSOPTAndroidTheme {
        SignUpCompose(viewModel = SignUpViewModel())
    }
}