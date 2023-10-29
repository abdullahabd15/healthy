package com.abdullah.kotlintechnicaltest.domain.usecases.auth

import android.content.SharedPreferences
import com.abdullah.kotlintechnicaltest.data.consts.DataConsts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveTokenToPreferenceUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    operator fun invoke(value: String) {
        sharedPreferences.edit().putString(DataConsts.TOKEN_KEY, value).apply()
    }
}