package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserInfo()
    }

    // 사용자 정보 세팅
    private fun setUserInfo(){
        val userId = intent.getStringExtra("userId")
        val userPw = intent.getStringExtra("userPw")
        val userName = intent.getStringExtra("userName")

        binding.userId.text = userId
        binding.userPw.text = userPw
        binding.tvName.text = userName
    }
}