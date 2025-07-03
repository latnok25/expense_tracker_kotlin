package com.latnok.expense_tracker_kotlin.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latnok.expense_tracker_kotlin.data.repository.AuthRepository
import com.latnok.expense_tracker_kotlin.data.storage.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel  @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _state = MutableLiveData<RegisterState>()
    val state: LiveData<RegisterState> get() = _state

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            _state.value = RegisterState.Loading
            try {
                val response = repository.register(name, email, password, confirmPassword)
                if (response.isSuccessful && response.body()?.token != null) {
                    tokenManager.saveToken(response.body()!!.token)
                    _state.value = RegisterState.Success
                } else {
                    _state.value = RegisterState.Error("Registration failed")
                }
            } catch (e: Exception) {
                _state.value = RegisterState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}

sealed class RegisterState {
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}