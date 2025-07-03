package com.latnok.expense_tracker_kotlin.data.repository

import com.latnok.expense_tracker_kotlin.data.api.ExpenseApi
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseApi: ExpenseApi
) {
    suspend fun getExpenses(token: String) = expenseApi.getExpenses("Bearer $token")
}