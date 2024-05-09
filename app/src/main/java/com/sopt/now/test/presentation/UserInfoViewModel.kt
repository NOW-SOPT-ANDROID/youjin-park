package com.sopt.now.test.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.test.data.ServicePool
import com.sopt.now.test.data.BaseState
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import com.sopt.now.test.data.dto.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }
    val liveData = MutableLiveData<BaseState>()
    val userInfoLiveData = MutableLiveData<ResponseUserInfoDto?>()

    init{
        userInfo()
    }

    fun userInfo() {
        userService.userInfo().enqueue(object : Callback<ResponseUserInfoDto> {
            override fun onResponse(
                call: Call<ResponseUserInfoDto>,
                response: Response<ResponseUserInfoDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserInfoDto? = response.body()
                    val userId = response.headers()["location"]
                    userInfoLiveData.value = data

                    liveData.value = BaseState(
                        isSuccess = true,
                        message = response.message()
                    )
                } else {
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseAuthDto::class.java)
                        liveData.value = BaseState(
                            isSuccess = false,
                            message = "회원 정보 조회 실패: ${errorResponse.message}" // 에러 메시지 사용
                        )
                    } catch (e: Exception) {
                        liveData.value = BaseState(
                            isSuccess = false,
                            message = "회원 정보 조회 실패: 에러 메시지 파싱 실패"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
                liveData.value = BaseState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }
}