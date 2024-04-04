package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

        binding.btnLogin.setOnClickListener {
            checkLogin()
        }

        // 회원가입 페이지로 이동
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
                userId = result.data!!.getStringExtra("userId").toString()
                userPw = result.data!!.getStringExtra("userPw").toString()
                userName = result.data!!.getStringExtra("userName").toString()
            }
        }
    }

    // 로그인
    private fun checkLogin(){
        var inputId = binding.etId.text.toString()
        var inputPw = binding.etPw.text.toString()

        if(inputId == userId && inputPw == userPw){
            moveToMain(userId, userPw, userName)
        }
    }

    // 메인 페이지로 이동
    private fun moveToMain(userId: String, userPw: String, userName: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("userId", userId)
            putExtra("userPw", userPw)
            putExtra("userName", userName)
        }
        startActivity(intent)
        Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
    }
}