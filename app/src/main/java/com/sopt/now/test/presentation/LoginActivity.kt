package com.sopt.now.test.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.test.data.dto.request.RequestLoginDto

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.initializeApiFactory(this)

        initObserver()
        setupLoginButton()
        setupSignUpTextView()
    }

    // 로그인
    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(RequestLoginDto(
                authenticationId = binding.etLoginId.text.toString(),
                password = binding.etLoginPw.text.toString()
            ))
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { response ->
            if(response.isSuccess){
                moveToMain()
            }
            showToast(response.message)
        }
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