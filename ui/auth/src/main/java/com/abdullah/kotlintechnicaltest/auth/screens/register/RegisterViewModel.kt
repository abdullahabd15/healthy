package com.abdullah.kotlintechnicaltest.auth.screens.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.abdullah.kotlintechnicaltest.domain.usecases.auth.RegisterUserCase
import com.abdullah.kotlintechnicaltest.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserCase: RegisterUserCase,
) : BaseViewModel() {
    private val _registerState = mutableStateOf(false)
    val registerState: State<Boolean> get() = _registerState

    fun register(
        firstName: String,
        lastName: String?,
        ktpNumber: String?,
        email: String?,
        phoneNumber: String?,
        password: String?,
        confirmPassword: String?
    ) {
        viewModelScope.launch {
            registerUserCase(
                firstName, lastName, ktpNumber, email, phoneNumber, password, confirmPassword
            ).collectLatest {
                if (it.isSuccess) {
                    _registerState.value = true
                    delay(1000)
                    _registerState.value = false
                } else if (it.isFailure) {
                    setErrorState(it.exceptionOrNull()?.message)
                }
            }
        }
    }
}