package com.sopt.now.test.data

import com.sopt.now.test.data.dto.request.RequestLoginDto
import com.sopt.now.test.data.dto.request.RequestSignUpDto
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun postSignUp(
        @Body request: RequestSignUpDto
    ): ResponseAuthDto

    @POST("member/login")
    fun postLogin(
        @Body request: RequestLoginDto,
    ): Call<ResponseAuthDto>
}