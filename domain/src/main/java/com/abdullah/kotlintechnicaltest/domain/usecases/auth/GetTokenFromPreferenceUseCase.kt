package com.abdullah.kotlintechnicaltest.domain.usecases.auth

import android.content.SharedPreferences
import com.abdullah.kotlintechnicaltest.data.consts.DataConsts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTokenFromPreferenceUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {
    operator fun invoke(): String? = sharedPreferences.getString(DataConsts.TOKEN_KEY, null)
}