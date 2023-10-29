package com.abdullah.kotlintechnicaltest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abdullah.kotlintechnicaltest.data.consts.DataConsts
import com.abdullah.kotlintechnicaltest.data.db.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM ${DataConsts.TABLE_USER} ORDER BY id DESC LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)
}