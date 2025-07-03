package com.latnok.expense_tracker_kotlin.data.model

data class RegisterRequest (
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)