package com.sopt.now

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var userId = ""
    var userPw = ""
    var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 화면으로 이동하는 람다 함수 정의
        val signUpAction = {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpLauncher.launch(intent) // ActivityResultLauncher 실행
        }

        // 회원가입 페이지로 이동
        binding.tvSignUp.setOnClickListener {
            signUpAction.invoke()
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
                userId = result.data!!.getStringExtra("userId").toString()
                userPw = result.data!!.getStringExtra("userPw").toString()
                userName = result.data!!.getStringExtra("userName").toString()
            }
        }
    }
}