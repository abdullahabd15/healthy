package com.abdullah.kotlintechnicaltest.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdullah.kotlintechnicaltest.data.consts.DataConsts

@Entity(tableName = DataConsts.TABLE_USER)
data class UserEntity(
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val ktpNumber: String? = null,
    val password: String? = null,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)