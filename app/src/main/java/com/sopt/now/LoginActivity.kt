package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.test.data.UserData

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var userData: UserData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickLoginButton()
        clickSignUpTextView()
    }

    // 로그인
    private fun clickLoginButton() {
        binding.btnLogin.setOnClickListener {
            checkLogin()
        }
    }

    // 회원가입 페이지로 이동
    private fun clickSignUpTextView() {
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpLauncher.launch(intent) // ActivityResultLauncher 실행
        }
    }

    // ActivityResultLauncher 초기화
    private val signUpLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        handleSignUpResult(result)
    }

    // 회원가입 결과 처리
    private fun handleSignUpResult(result: ActivityResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                result.data?.getParcelableExtra<UserData>("userData")?.let { data ->
                    userData = data
                }
            }
        }
    }

    // 로그인
    private fun checkLogin(){
        userData?.let { userData ->
            val inputId = binding.etLoginId.text.toString()
            val inputPw = binding.etLoginPw.text.toString()

            // 아이디, 비번 확인
            if (inputId == userData.userId && inputPw == userData.userPw) {
                moveToMain(userData)
            }
            else {
                Toast.makeText(this, "아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 메인 페이지로 이동
    private fun moveToMain(userData: UserData) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("userData", userData)
        }
        startActivity(intent)
        Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
    }
}