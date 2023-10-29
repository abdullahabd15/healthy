package com.abdullah.kotlintechnicaltest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abdullah.kotlintechnicaltest.data.consts.DataConsts
import com.abdullah.kotlintechnicaltest.data.db.dao.UserDao
import com.abdullah.kotlintechnicaltest.data.db.entities.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = DataConsts.DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DataConsts.DB_NAME
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}