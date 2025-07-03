package com.latnok.expense_tracker_kotlin.data.model

data class Expense(
    val id: Int,
    val item_name: String,
    val item_amount: String,
    val category: String,
    val expense_date: String
)