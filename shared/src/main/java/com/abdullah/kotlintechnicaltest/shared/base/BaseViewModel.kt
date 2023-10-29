package com.abdullah.kotlintechnicaltest.shared.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

open class BaseViewModel : ViewModel() {

    private val _errorState = MutableStateFlow("")
    val errorState get() = _errorState.asSharedFlow()

    suspend fun setErrorState(error: String?) {
        _errorState.value = error ?: "Unknown Error"
        delay(1000)
        _errorState.value = ""
    }
}