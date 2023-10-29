package com.abdullah.kotlintechnicaltest.domain.model

import com.abdullah.kotlintechnicaltest.data.db.entities.UserEntity

data class UserData(
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phoneNumber: String?,
    val ktpNumber: String?,
    val password: String?,
)

fun UserEntity.mapToUserData(): UserData {
    return UserData(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        ktpNumber = ktpNumber,
        password = password
    )
}
