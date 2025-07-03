package com.latnok.expense_tracker_kotlin.data.repository

import com.latnok.expense_tracker_kotlin.data.api.AuthApi
import com.latnok.expense_tracker_kotlin.data.model.LoginRequest
import com.latnok.expense_tracker_kotlin.data.model.RegisterRequest
import com.latnok.expense_tracker_kotlin.data.model.RegisterResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi
) {
    suspend fun login(email: String, password: String) =
        api.login(LoginRequest(email, password))

    suspend fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Response<RegisterResponse> {
        val request = RegisterRequest(name, email, password, confirmPassword)
        return api.register(request)
    }
}