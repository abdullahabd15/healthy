package com.abdullah.kotlintechnicaltest.profile.screens

import androidx.lifecycle.viewModelScope
import com.abdullah.kotlintechnicaltest.domain.model.UserData
import com.abdullah.kotlintechnicaltest.domain.usecases.profile.GetUserUseCase
import com.abdullah.kotlintechnicaltest.domain.usecases.profile.UpdateUserUseCase
import com.abdullah.kotlintechnicaltest.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : BaseViewModel() {

    private val _userState = MutableStateFlow<UserData?>(null)
    val userState get() = _userState.asSharedFlow()

    private val _updateUserState = MutableStateFlow(false)
    val updateUserState get() = _updateUserState.asSharedFlow()

    fun getProfile() {
        viewModelScope.launch {
            getUserUseCase().collectLatest {
                if (it.isSuccess) {
                    it.getOrNull()?.let { user ->
                        _userState.emit(user)
                    } ?: run {
                        setErrorState("User not found")
                    }
                } else if (it.isFailure) {
                    setErrorState(it.exceptionOrNull()?.message)
                }
            }
        }
    }

    fun updateProfile(
        id: Int?,
        firstName: String,
        lastName: String?,
        ktpNumber: String?,
        email: String?,
        phoneNumber: String?,
    ) {
        viewModelScope.launch {
            updateUserUseCase(
                id = id,
                firstName = firstName,
                lastName = lastName,
                ktpNumber = ktpNumber,
                email = email,
                phoneNumber = phoneNumber
            ).collectLatest {
                if (it.isSuccess && it.getOrNull() != null) {
                    _updateUserState.value = true
                    _userState.emit(it.getOrNull())
                    delay(1000)
                    _updateUserState.value = false
                } else if (it.isFailure) {
                    setErrorState(it.exceptionOrNull()?.message)
                }
            }
        }
    }
}