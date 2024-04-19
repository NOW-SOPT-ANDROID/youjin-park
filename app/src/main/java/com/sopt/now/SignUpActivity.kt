package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.test.data.UserData

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickSignUpButton()
    }

    // 회원 가입
    private fun clickSignUpButton() {
        binding.btnSignUp.setOnClickListener {
            with(binding) {
                val userId = etSignUpId.text.toString()
                val userPw = etSignUpPw.text.toString()
                val userName = etSignUpName.text.toString()
                val selfDescription = etSignUpDescription.text.toString()

                val userData = UserData(userId, userPw, userName, selfDescription)
                checkSignUp(userData)
            }
        }
    }

    // 회원 가입 가능 여부 체크
    private fun checkSignUp(userData: UserData) {
        val isValidId = userData.userId.length in 6..10
        val isValidPw = userData.userPw.length in 8..12
        val isValidName = userData.userName.trim().isEmpty() // 공백으로만 이루어진 경우 판단

        val message = when {
            !isValidId -> "ID는 6~10 글자여야 합니다."
            !isValidPw -> "Password는 8~12 글자여야 합니다."
            isValidName -> "공백으로만 이루어진 닉네임은 불가합니다."
            else -> {
                moveToLogin(userData)
                "회원가입 성공!"
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // 로그인 페이지로 이동
    private fun moveToLogin(userData: UserData) {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra("userData", userData)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}