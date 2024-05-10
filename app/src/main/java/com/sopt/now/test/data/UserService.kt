package com.sopt.now.test.data

import com.sopt.now.test.data.dto.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("member/info")
    fun getUserInfo(
    ): Call<ResponseUserInfoDto>
}