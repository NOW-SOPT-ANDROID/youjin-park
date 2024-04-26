package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.test.data.UserData
import com.sopt.now.test.data.UserViewModel
import com.sopt.now.test.data.UserViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    private var userData: UserData ?= null
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = UserViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        setSignUpLauncher()
        setupLoginButton()
        setupSignUpTextView()
    }

    // 로그인
    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            userData?.let { checkLogin(it) }
        }
    }

    // 회원가입 페이지로 이동
    private fun setupSignUpTextView() {
        binding.tvSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).also {
                signUpLauncher.launch(it) // ActivityResultLauncher 실행
            }
        }
    }

    // 로그인
    private fun checkLogin(userData: UserData) {
        val inputId = binding.etLoginId.text.toString()
        val inputPw = binding.etLoginPw.text.toString()

        // 아이디, 비번 확인
        if (inputId == userData.userId && inputPw == userData.userPw) {
            showToast("로그인 성공!")
            moveToMain()
        } else {
            showToast("아이디 또는 비밀번호가 잘못되었습니다.")
        }
    }

    // 메인 페이지로 이동
    private fun moveToMain() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
    private fun setSignUpLauncher(){
        signUpLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                userData = viewModel.getUserData()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}