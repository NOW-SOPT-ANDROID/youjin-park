package com.sopt.now.test.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.test.core.util.context.showToast
import com.sopt.now.test.data.dto.request.RequestLoginDto

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loginViewModel.initializeApiFactory(this)

        initLoginBtnClickListener()
        initPostLoginObserver()
        initSignUpBtnClickListener()
    }

    // 로그인
    private fun initLoginBtnClickListener() {
        with(binding){
            btnLogin.setOnClickListener {
                loginViewModel.postLogin(RequestLoginDto(
                    authenticationId = etLoginId.text.toString(),
                    password = etLoginPw.text.toString()
                ))
            }
        }
    }

    private fun initPostLoginObserver() {
        loginViewModel.postLoginLiveData.observe(this) { response ->
            if(response.isSuccess){
                navigateToMain(response.message)
            }
            showToast(response.message)
        }
    }

    // 메인 페이지로 이동
    private fun navigateToMain(content: String) {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
        showToast(content)
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