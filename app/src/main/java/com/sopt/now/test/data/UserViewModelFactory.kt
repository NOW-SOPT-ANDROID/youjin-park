package com.sopt.now.test.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// ViewModel에 context를 전달하기 위한 ViewModelProvider.Factory 구현체
class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            // UserViewModel 클래스와 매칭되면 생성된 ViewModel에 context를 전달하여 반환
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}