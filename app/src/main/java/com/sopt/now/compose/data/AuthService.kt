package com.sopt.now.compose.data

import com.sopt.now.compose.data.dto.request.RequestLoginDto
import com.sopt.now.compose.data.dto.request.RequestSignUpDto
import com.sopt.now.compose.data.dto.response.ResponseAuthDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseAuthDto>

    @POST("member/login")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseAuthDto>
}