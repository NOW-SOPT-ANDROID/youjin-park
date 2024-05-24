package com.sopt.now.test.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.test.core.util.context.showToast
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.dto.request.RequestSignUpDto
import timber.log.Timber

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initSignUpBtnClickListener()
        initPostSignUpObserver()
    }

    // 회원가입
    private fun initSignUpBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            signUpViewModel.postSignUp(getSignUpRequestDto())
        }
    }

    private fun initPostSignUpObserver() {
        signUpViewModel.postSignUpLiveData.observe(this) {
            when (it) {
                is UiState.Success -> navigateToLogin(it.data.message)
                is UiState.Failure -> showToast(it.errorMessage)
                is UiState.Loading -> Timber.d("로딩 중")
            }
        }
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        with(binding){
            val id = etSignUpId.text.toString()
            val password = etSignUpPw.text.toString()
            val nickname = etSignUpName.text.toString()
            val phoneNumber = etSignUpPhone.text.toString()
            return RequestSignUpDto(
                authenticationId = id,
                password = password,
                nickname = nickname,
                phone = phoneNumber
            )
        }
    }

    // 로그인 페이지로 이동
    private fun navigateToLogin(content: String) {
        Intent(this, LoginActivity::class.java).apply {
            startActivity(this)
            finish()
        }
        showToast(content)
    }
}