package com.abdullah.kotlintechnicaltest.main.screens.sidemenu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.abdullah.kotlintechnicaltest.domain.model.UserData
import com.abdullah.kotlintechnicaltest.domain.usecases.profile.GetUserUseCase
import com.abdullah.kotlintechnicaltest.domain.usecases.auth.SaveTokenToPreferenceUseCase
import com.abdullah.kotlintechnicaltest.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SideMenuViewModel @Inject constructor(
    private val saveTokenToPreferenceUseCase: SaveTokenToPreferenceUseCase,
    private val getUserUseCase: GetUserUseCase
): BaseViewModel() {

    private val _logoutState = mutableStateOf(false)
    val logoutState: State<Boolean> get() = _logoutState

    private val _userState = mutableStateOf<UserData?>(null)
    val userState: State<UserData?> get() = _userState

    fun logout() {
        viewModelScope.launch {
            saveTokenToPreferenceUseCase.invoke("")
            _logoutState.value = true
        }
    }

    fun getUser() {
        viewModelScope.launch {
            getUserUseCase().collectLatest {
                if (it.isSuccess) {
                    it.getOrNull()?.let { user ->
                        _userState.value = user
                    } ?: run {
                        setErrorState("User not found")
                    }
                } else if (it.isFailure) {
                    setErrorState(it.exceptionOrNull()?.message)
                }
            }
        }
    }
}