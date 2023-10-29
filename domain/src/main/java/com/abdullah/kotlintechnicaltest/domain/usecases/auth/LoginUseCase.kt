package com.abdullah.kotlintechnicaltest.domain.usecases.auth

import android.content.res.Resources.NotFoundException
import com.abdullah.kotlintechnicaltest.data.model.auth.LoginBody
import com.abdullah.kotlintechnicaltest.data.repositories.local.UserLocalRepository
import com.abdullah.kotlintechnicaltest.data.repositories.remote.AuthRemoteRepository
import com.abdullah.kotlintechnicaltest.domain.exceptions.ApiRequestException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val authRemoteRepository: AuthRemoteRepository,
    private val userLocalRepository: UserLocalRepository,
) {
    suspend operator fun invoke(username: String, password: String): Flow<Result<String?>> =
        flow {
            try {
                val response = authRemoteRepository.login(LoginBody(username, password))
                if (response.isSuccessful) {
                    val user = userLocalRepository.getUser()
                    user?.let {
                        emit(Result.success(response.body()?.token))
                    } ?: run {
                        emit(Result.failure(NotFoundException("User not found")))
                    }
                } else
                    emit(Result.failure(ApiRequestException("Login Failed!")))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
}