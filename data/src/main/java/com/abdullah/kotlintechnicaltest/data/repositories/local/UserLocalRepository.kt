package com.abdullah.kotlintechnicaltest.data.repositories.local

import com.abdullah.kotlintechnicaltest.data.db.dao.UserDao
import com.abdullah.kotlintechnicaltest.data.db.entities.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalRepository @Inject constructor(
    private val userDao: UserDao,
) {
    suspend fun getUser(): UserEntity? = userDao.getUser()
    suspend fun saveUser(user: UserEntity) = userDao.saveUser(user)
    suspend fun updateUser(user: UserEntity) = userDao.updateUser(user)
}