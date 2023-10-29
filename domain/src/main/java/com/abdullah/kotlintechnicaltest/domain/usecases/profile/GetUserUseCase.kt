package com.abdullah.kotlintechnicaltest.domain.usecases.profile

import android.content.res.Resources.NotFoundException
import com.abdullah.kotlintechnicaltest.data.repositories.local.UserLocalRepository
import com.abdullah.kotlintechnicaltest.domain.model.UserData
import com.abdullah.kotlintechnicaltest.domain.model.mapToUserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCase @Inject constructor(
    private val userLocalRepository: UserLocalRepository,
) {
    suspend operator fun invoke(): Flow<Result<UserData>> = flow {
        try {
            val userEntity = userLocalRepository.getUser()
            userEntity?.let {
                val user = it.mapToUserData()
                emit(Result.success(user))
            } ?: run {
                emit(Result.failure(NotFoundException("User not found")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}