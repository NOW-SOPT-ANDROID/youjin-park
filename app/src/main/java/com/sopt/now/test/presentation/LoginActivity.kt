package com.sopt.now.test.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.test.data.ApiFactory
import com.sopt.now.test.data.UserPreference
import com.sopt.now.test.data.dto.request.RequestLoginDto

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userPreference = UserPreference(this)
        ApiFactory.initializeUserPreference(userPreference)

        initObserver()
        setupLoginButton()
        setupSignUpTextView()
    }

    // 로그인
    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(getLoginRequestDto())
        }
    }

    private fun initObserver() {
        // 사용자 데이터 저장
        viewModel.userIdLiveData.observe(this) { userId ->
            userId?.let {
                userPreference.saveUserId(userId)
                moveToMain()
            }
        }

        viewModel.liveData.observe(this) { response ->
            showToast(response.message)
        }
    }

    private fun getLoginRequestDto(): RequestLoginDto {
        val id = binding.etLoginId.text.toString()
        val password = binding.etLoginPw.text.toString()
        return RequestLoginDto(
            authenticationId = id,
            password = password
        )
    }

    // 메인 페이지로 이동
    private fun moveToMain() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }

    // 회원가입 페이지로 이동
    private fun setupSignUpTextView() {
        binding.tvSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}