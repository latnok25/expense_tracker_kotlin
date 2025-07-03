package com.latnok.expense_tracker_kotlin.data.api

import com.latnok.expense_tracker_kotlin.data.model.LoginRequest
import com.latnok.expense_tracker_kotlin.data.model.LoginResponse
import com.latnok.expense_tracker_kotlin.data.model.RegisterRequest
import com.latnok.expense_tracker_kotlin.data.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
