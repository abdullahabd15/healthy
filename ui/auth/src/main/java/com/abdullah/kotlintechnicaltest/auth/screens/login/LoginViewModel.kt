package com.abdullah.kotlintechnicaltest.auth.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.abdullah.kotlintechnicaltest.domain.usecases.auth.GetTokenFromPreferenceUseCase
import com.abdullah.kotlintechnicaltest.domain.usecases.auth.LoginUseCase
import com.abdullah.kotlintechnicaltest.domain.usecases.auth.SaveTokenToPreferenceUseCase
import com.abdullah.kotlintechnicaltest.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenToPreferenceUseCase: SaveTokenToPreferenceUseCase,
    private val getTokenFromPreferenceUseCase: GetTokenFromPreferenceUseCase
) : BaseViewModel() {

    private val _loginState = mutableStateOf(false)
    val loginState: State<Boolean> get() = _loginState

    init {
        checkIsLoggedIn()
    }

    fun login(
        username: String,
        password: String,
    ) {
        viewModelScope.launch {
            loginUseCase.invoke(username, password).collectLatest {
                if (it.isSuccess) {
                    val token = it.getOrNull()
                    if (token != null) {
                        saveTokenToPreferenceUseCase.invoke(token)
                        _loginState.value = true
                    } else {
                        _loginState.value = false
                        setErrorState("Unauthorized")
                    }
                } else {
                    _loginState.value = false
                    setErrorState(it.exceptionOrNull()?.message)
                }
            }
        }
    }

    private fun checkIsLoggedIn() {
        val isLoggedIn =  !getTokenFromPreferenceUseCase().isNullOrBlank()
        _loginState.value = isLoggedIn
    }
}