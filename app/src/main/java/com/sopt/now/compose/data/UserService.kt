package com.sopt.now.compose.data

import com.sopt.now.compose.data.dto.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("member/info")
    fun userInfo(
    ): Call<ResponseUserInfoDto>
}