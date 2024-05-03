package com.sopt.now.test.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.test.data.UserData
import com.sopt.now.test.data.UserPreference

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference(this)
        setSignUpLauncher()
        setupLoginButton()
        setupSignUpTextView()
    }

    // 로그인
    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            checkLogin()
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
    private fun checkLogin() {
        val inputId = binding.etLoginId.text.toString()
        val inputPw = binding.etLoginPw.text.toString()
        val userData = userPreference.getUserData()

        // 아이디, 비번 확인
        if (userData != null) {
            if (inputId == userData.userId && inputPw == userData.userPw) {
                showToast("로그인 성공!")
                moveToMain()
            } else {
                showToast("아이디 또는 비밀번호가 잘못되었습니다.")
            }
        }
    }

    // 메인 페이지로 이동
    private fun moveToMain() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }

    // 사용자 데이터 저장
    private fun setSignUpLauncher(){
        signUpLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getParcelableExtra<UserData>("userData")?.let {
                    userPreference.saveUserData(it)
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}