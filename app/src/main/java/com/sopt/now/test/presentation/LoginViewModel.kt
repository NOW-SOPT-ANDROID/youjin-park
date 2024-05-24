package com.sopt.now.test.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.test.data.ApiFactory
import com.sopt.now.test.data.BaseState
import com.sopt.now.test.data.ServicePool
import com.sopt.now.test.data.UserPreference
import com.sopt.now.test.data.dto.request.RequestLoginDto
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    private val _postLoginLiveData: MutableLiveData<BaseState> = MutableLiveData()
    val postLoginLiveData: MutableLiveData<BaseState> = _postLoginLiveData
    private lateinit var userPreference: UserPreference

    fun postLogin(request: RequestLoginDto) {
        authService.postLogin(request).enqueue(object : Callback<ResponseAuthDto> {
            override fun onResponse(
                call: Call<ResponseAuthDto>,
                response: Response<ResponseAuthDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseAuthDto? = response.body()
                    val userId = response.headers()["location"]
                    if (userId != null) {
                        userPreference.saveUserId(userId)
                    }
                    _postLoginLiveData.value = BaseState(
                        isSuccess = true,
                        message = "로그인 성공! 유저의 ID는 $userId 입니다."
                    )
                    Log.d("Login", "data: $data, userId: $userId")
                } else {
                    val error = response.message()
                    _postLoginLiveData.value = BaseState(
                        isSuccess = false,
                        message = "로그인 실패 $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseAuthDto>, t: Throwable) {
                _postLoginLiveData.value = BaseState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }

    // 초기화
    fun initializeApiFactory(context: Context) {
        userPreference = UserPreference(context)
        ApiFactory.initializeUserPreference(userPreference)
    }
}