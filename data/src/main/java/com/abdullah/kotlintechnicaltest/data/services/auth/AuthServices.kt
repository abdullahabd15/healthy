package com.abdullah.kotlintechnicaltest.data.services.auth

import com.abdullah.kotlintechnicaltest.data.model.auth.LoginBody
import com.abdullah.kotlintechnicaltest.data.model.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServices {

    @POST("login")
    suspend fun login(
        @Body loginBody: LoginBody,
    ): Response<LoginResponse>
}