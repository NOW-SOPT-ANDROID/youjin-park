package com.sopt.now.test.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.R
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.test.core.util.context.showToast
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.ApiFactory
import com.sopt.now.test.data.UserPreference
import com.sopt.now.test.data.dto.request.RequestLoginDto
import com.sopt.now.test.presentation.MainActivity
import com.sopt.now.test.presentation.signup.SignUpActivity
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeUserPreference()
        initLoginBtnClickListener()
        initPostLoginObserver()
        initSignUpBtnClickListener()
    }

    // UserPreference 초기화
    private fun initializeUserPreference() {
        userPreference = UserPreference(this)
        ApiFactory.initializeUserPreference(userPreference)
    }

    // 로그인
    private fun initLoginBtnClickListener() {
        with(binding) {
            btnLogin.setOnClickListener {
                loginViewModel.postLogin(getLoginRequestDto())
            }
        }
    }

    private fun getLoginRequestDto(): RequestLoginDto {
        with(binding) {
            val id = etLoginId.text.toString()
            val password = etLoginPw.text.toString()
            return RequestLoginDto(
                authenticationId = id,
                password = password
            )
        }
    }

    private fun initPostLoginObserver() {
        loginViewModel.postLoginLiveData.observe(this) {
            when (it) {
                is UiState.Success -> {
                    userPreference.getUserId()?.let {
                        navigateToMain(it)
                    }
                }
                is UiState.Failure -> showToast(it.errorMessage)
                is UiState.Loading -> Timber.d(getString(R.string.message_loading))
            }
        }
    }

    // 메인 페이지로 이동
    private fun navigateToMain(userId: String) {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
        showToast(getString(R.string.message_login_success, userId))
    }

    // 회원가입 페이지로 이동
    private fun initSignUpBtnClickListener() {
        binding.tvSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
    }
}