package com.sopt.now

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원 가입
        binding.btnSignUp.setOnClickListener {
            val userId = binding.etId.text.toString()
            val userPw = binding.etPw.text.toString()
            val userName = binding.etName.text.toString()
            val userMbti = binding.etMbti.text.toString()

            checkSignUp(userId, userPw, userName, userMbti)
        }
    }

    // 회원 가입 가능 여부 체크
    private fun checkSignUp(userId: String, userPw: String, userName: String, userMbti: String) {
        val isValidId = userId.length in 6..10
        val isValidPw = userPw.length in 8..12
        val isValidName = userName.trim().isEmpty() // 공백으로만 이루어진 경우 판단
        val isEmpty = userId.isEmpty() || userId.isEmpty() || userId.isEmpty() || userMbti.isEmpty()

        val message = when {
            !isValidId -> "ID는 6~10 글자여야 합니다."
            !isValidPw -> "Password는 8~12 글자여야 합니다."
            isValidName -> "공백으로만 이루어진 닉네임은 불가합니다."
            isEmpty -> "모든 정보를 입력해주세요."
            else -> "회원가입 성공!"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}