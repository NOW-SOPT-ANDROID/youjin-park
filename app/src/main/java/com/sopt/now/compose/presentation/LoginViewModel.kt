package com.sopt.now.compose.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.BaseState
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.dto.request.RequestLoginDto
import com.sopt.now.compose.data.dto.response.ResponseAuthDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<BaseState>()
    val userIdLiveData = MutableLiveData<String?>()

    fun login(request: RequestLoginDto) {
        authService.login(request).enqueue(object : Callback<ResponseAuthDto> {
            override fun onResponse(
                call: Call<ResponseAuthDto>,
                response: Response<ResponseAuthDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseAuthDto? = response.body()
                    val userId = response.headers()["location"]
                    userIdLiveData.value = userId
                    liveData.value = BaseState(
                        isSuccess = true,
                        message = "로그인 성공! 유저의 ID는 $userId 입니다."
                    )
                    Log.d("Login", "data: $data, userId: $userId")
                } else {
                    val error = response.message()
                    liveData.value = BaseState(
                        isSuccess = false,
                        message = "로그인 실패 $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseAuthDto>, t: Throwable) {
                liveData.value = BaseState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }
}