package com.sopt.now.test.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.test.data.ServicePool
import com.sopt.now.test.data.BaseState
import com.sopt.now.test.data.dto.request.RequestSignUpDto
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<BaseState>()

    fun signUp(request: RequestSignUpDto) {
        authService.postSignUp(request).enqueue(object : Callback<ResponseAuthDto> {
            override fun onResponse(
                call: Call<ResponseAuthDto>,
                response: Response<ResponseAuthDto>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    liveData.value = BaseState(
                        isSuccess = true,
                        message = "회원가입 성공 유저의 ID는 $userId 입니다."
                    )
                } else {
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseAuthDto::class.java)
                        liveData.value = BaseState(
                            isSuccess = false,
                            message = "회원가입 실패: ${errorResponse.message}" // 에러 메시지 사용
                        )
                    } catch (e: Exception) {
                        liveData.value = BaseState(
                            isSuccess = false,
                            message = "회원가입 실패: 에러 메시지 파싱 실패"
                        )
                    }
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