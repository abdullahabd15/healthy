package com.abdullah.kotlintechnicaltest.data.di

import android.content.Context
import android.content.SharedPreferences
import com.abdullah.kotlintechnicaltest.data.BuildConfig
import com.abdullah.kotlintechnicaltest.data.db.AppDatabase
import com.abdullah.kotlintechnicaltest.data.db.dao.UserDao
import com.abdullah.kotlintechnicaltest.data.services.auth.AuthServices
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().create()
    )

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("base-url") baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).build()

    @Provides
    @Singleton
    fun provideAuthService(
        retrofit: Retrofit,
    ): AuthServices = retrofit.create(AuthServices::class.java)

    @Provides
    @Singleton
    @Named("base-url")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    @Named("preference-name")
    fun providePreferenceName(): String = BuildConfig.PREFERENCE_NAME

    @Provides
    @Singleton
    fun provideSharedPreference(
        @ApplicationContext context: Context,
        @Named("preference-name") prefName: String,
    ): SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideUserDao(
        db: AppDatabase,
    ): UserDao = db.userDao()
}