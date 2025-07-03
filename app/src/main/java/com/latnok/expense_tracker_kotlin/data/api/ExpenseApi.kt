package com.latnok.expense_tracker_kotlin.data.api

import com.latnok.expense_tracker_kotlin.data.model.ExpenseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ExpenseApi {
    @GET("expense")
    suspend fun getExpenses(@Header("Authorization") token: String): Response<ExpenseResponse>
}