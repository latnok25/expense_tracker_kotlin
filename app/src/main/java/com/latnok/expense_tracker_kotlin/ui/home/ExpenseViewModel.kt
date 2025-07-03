package com.latnok.expense_tracker_kotlin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latnok.expense_tracker_kotlin.data.model.Expense
import com.latnok.expense_tracker_kotlin.data.repository.ExpenseRepository
import com.latnok.expense_tracker_kotlin.data.storage.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.first

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val repository: ExpenseRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> get() = _expenses

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun loadExpenses() {
        viewModelScope.launch {
            try {
                val token = tokenManager.token.first()
                val response = repository.getExpenses(token!!)
                if (response.isSuccessful) {
                    _expenses.value = response.body()?.enpenses ?: emptyList()
                } else {
                    _error.value = "Failed to load expenses"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}


