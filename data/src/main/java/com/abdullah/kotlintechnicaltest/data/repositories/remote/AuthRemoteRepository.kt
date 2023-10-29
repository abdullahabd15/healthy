package com.abdullah.kotlintechnicaltest.data.repositories.remote

import com.abdullah.kotlintechnicaltest.data.model.auth.LoginBody
import com.abdullah.kotlintechnicaltest.data.model.auth.LoginResponse
import com.abdullah.kotlintechnicaltest.data.services.auth.AuthServices
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteRepository @Inject constructor(
    private val authServices: AuthServices,
) {
    suspend fun login(body: LoginBody): Response<LoginResponse> = authServices.login(body)
}