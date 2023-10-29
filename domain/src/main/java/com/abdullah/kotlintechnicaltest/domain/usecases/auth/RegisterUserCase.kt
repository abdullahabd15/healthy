package com.abdullah.kotlintechnicaltest.domain.usecases.auth

import com.abdullah.kotlintechnicaltest.data.db.entities.UserEntity
import com.abdullah.kotlintechnicaltest.data.repositories.local.UserLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUserCase @Inject constructor(
    private val userLocalRepository: UserLocalRepository,
) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String?,
        ktpNumber: String?,
        email: String?,
        phoneNumber: String?,
        password: String?,
        confirmPassword: String?
    ): Flow<Result<Boolean>> = flow {
        try {
            if (password != confirmPassword) {
                emit(Result.failure(Exception("Password not match!")))
                return@flow
            }
            val user = UserEntity(
                firstName = firstName,
                lastName = lastName,
                ktpNumber = ktpNumber,
                email = email,
                phoneNumber = phoneNumber,
                password = password
            )
            userLocalRepository.saveUser(user)
            emit(Result.success(true))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}