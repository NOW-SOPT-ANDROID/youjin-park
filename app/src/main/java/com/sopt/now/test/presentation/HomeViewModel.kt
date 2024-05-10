package com.sopt.now.test.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.test.data.BaseState
import com.sopt.now.test.data.ServicePool
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import com.sopt.now.test.data.dto.response.ResponseFriendDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    private val friendService by lazy { ServicePool.friendService }
    val liveData = MutableLiveData<BaseState>()
    val friendInfoLiveData = MutableLiveData<ResponseFriendDto?>()

    fun getFriendInfo(page: Int) {
        friendService.getFriendInfo(page).enqueue(object : Callback<ResponseFriendDto> {
            override fun onResponse(
                call: Call<ResponseFriendDto>,
                response: Response<ResponseFriendDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseFriendDto? = response.body()
                    friendInfoLiveData.value = data
                    liveData.value = BaseState(
                        isSuccess = true,
                        message = response.message()
                    )
                    Log.d("HomeViewModel", "Response: $data")
                } else {
                    Log.e("HomeViewModel", "Failed to get friend info: ${response.code()}")
                    val error = response.errorBody()?.string()
                    val gson = Gson()
                    try {
                        val errorResponse = gson.fromJson(error, ResponseAuthDto::class.java)
                        liveData.value = BaseState(
                            isSuccess = false,
                            message = "친구 정보 조회 실패: ${errorResponse.message}" // 에러 메시지 사용
                        )
                    } catch (e: Exception) {
                        liveData.value = BaseState(
                            isSuccess = false,
                            message = "친구 정보 조회 실패: 에러 메시지 파싱 실패"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<ResponseFriendDto>, t: Throwable) {
                liveData.value = BaseState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }
}