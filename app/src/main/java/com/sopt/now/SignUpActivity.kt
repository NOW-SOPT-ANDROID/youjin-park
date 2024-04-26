package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.test.data.UserViewModel
import com.sopt.now.test.data.UserData
import com.sopt.now.test.data.UserViewModelFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = UserViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        setupSignUpButton()
    }

    // 회원 가입
    private fun setupSignUpButton() {
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
        val isValidId = userData.userId.length in MIN_ID_LENGTH..MAX_ID_LENGTH
        val isValidPw = userData.userPw.length in MIN_PW_LENGTH..MAX_PW_LENGTH
        val isValidName = userData.userName.isBlank() // 공백으로만 이루어진 경우 판단

        val message = when {
            !isValidId -> "ID는 6~10 글자여야 합니다."
            !isValidPw -> "Password는 8~12 글자여야 합니다."
            isValidName -> "공백으로만 이루어진 닉네임은 불가합니다."
            else -> {
                moveToLogin(userData)
                "회원가입 성공!"
            }
        }
        viewModel.setUserData(userData)
        showToast(message)
    }

    // 로그인 페이지로 이동
    private fun moveToLogin(userData: UserData) {
        Intent(this, LoginActivity::class.java).apply {
            putExtra("userData", userData)
            setResult(RESULT_OK, this)
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // 글자 수 제한
    companion object {
        private const val MIN_ID_LENGTH = 6
        private const val MAX_ID_LENGTH = 10
        private const val MIN_PW_LENGTH = 8
        private const val MAX_PW_LENGTH = 12
    }
}