package com.sopt.now.test.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import java.io.IOException

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL
    private lateinit var userPreference: UserPreference // UserPreference 추가

    // UserPreference 초기화
    fun initializeUserPreference(userPreference: UserPreference) {
        this.userPreference = userPreference
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(HeaderInterceptor()))
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private fun provideOkHttpClient(interceptor: HeaderInterceptor): OkHttpClient
            = OkHttpClient.Builder().run {
        addInterceptor(interceptor)
        build()
    }

    class HeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("memberId", userPreference.getUserId().toString())
                .build()
            proceed(newRequest)
        }
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val userService = ApiFactory.create<UserService>()
}