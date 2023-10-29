package com.abdullah.kotlintechnicaltest.domain.usecases.profile

import com.abdullah.kotlintechnicaltest.data.db.entities.UserEntity
import com.abdullah.kotlintechnicaltest.data.repositories.local.UserLocalRepository
import com.abdullah.kotlintechnicaltest.domain.model.UserData
import com.abdullah.kotlintechnicaltest.domain.model.mapToUserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateUserUseCase @Inject constructor(
    private val userLocalRepository: UserLocalRepository,
) {
    suspend operator fun invoke(
        id: Int?,
        firstName: String,
        lastName: String?,
        ktpNumber: String?,
        email: String?,
        phoneNumber: String?,
    ): Flow<Result<UserData?>> = flow {
        try {
            val user = UserEntity(
                id = id,
                firstName = firstName,
                lastName = lastName,
                ktpNumber = ktpNumber,
                email = email,
                phoneNumber = phoneNumber
            )
            userLocalRepository.updateUser(user)
            val updatedUser = userLocalRepository.getUser()?.mapToUserData()
            emit(Result.success(updatedUser))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}