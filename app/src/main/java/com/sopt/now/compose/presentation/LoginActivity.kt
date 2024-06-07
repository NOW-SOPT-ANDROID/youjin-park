package com.sopt.now.compose.presentation

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
import com.sopt.now.compose.R
import com.sopt.now.compose.data.ApiFactory
import com.sopt.now.compose.data.UserPreference
import com.sopt.now.compose.data.dto.request.RequestLoginDto
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {

    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    userPreference = UserPreference(LocalContext.current)
                    if (!ApiFactory.isInitialized) {
                        ApiFactory.initializeUserPreference(userPreference)
                    }

                    val viewModel: LoginViewModel = remember { LoginViewModel() }
                    LoginView(viewModel)
                }
            }
        }
    }
}

@Composable
fun LoginView(
    viewModel: LoginViewModel
) {
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
        LoginInputField(
            label = stringResource(id = R.string.tv_id),
            inputData = inputId,
            isPassword = false
        ) { inputId = it }
        Spacer(modifier = Modifier.height(60.dp))
        LoginInputField(
            label = stringResource(id = R.string.tv_pw),
            inputData = inputPw,
            isPassword = true
        ) { inputPw = it }
        Spacer(modifier = Modifier.weight(2f))
        LoginButton(
            text = stringResource(id = R.string.btn_login),
            onClick = {
                checkLogin(context, viewModel, inputId, inputPw)
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        LoginButton(
            stringResource(id = R.string.btn_sign_up)) {
            Intent(context, SignUpActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}

@Composable
fun LoginInputField(
    label: String,
    inputData: String,
    isPassword: Boolean,
    onValueChange: (String) -> Unit
) {
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
            value = inputData,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            singleLine = true,
            visualTransformation =
            if (isPassword)
                PasswordVisualTransformation()
            else VisualTransformation.None,
            keyboardOptions =
            if (isPassword)
                KeyboardOptions(keyboardType = KeyboardType.Password)
            else KeyboardOptions.Default
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

private fun checkLogin(
    context: Context,
    viewModel: LoginViewModel,
    inputId: String,
    inputPw: String
) {
    viewModel.login(RequestLoginDto(inputId, inputPw))

    val userId = viewModel.userIdLiveData.value
    if(userId != null){
        ApiFactory.userPreference.saveUserId(userId)
        moveToMain(context)
    }
}

private fun moveToMain(
    context: Context,
) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)

    Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    NOWSOPTAndroidTheme {
        LoginView(viewModel = LoginViewModel())
    }
}