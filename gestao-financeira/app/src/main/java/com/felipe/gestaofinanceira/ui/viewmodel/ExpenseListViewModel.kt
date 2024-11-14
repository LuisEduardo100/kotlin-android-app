package com.felipe.gestaofinanceira.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExpenseListViewModel(
    private val expenseRepository: ExpenseRepository = ExpenseRepository()
): ViewModel() {

    private var _listOfExpense = MutableStateFlow(listOf<Expense>())
    val listOfExpense = _listOfExpense.asStateFlow()

    fun getExpenseList() {
        viewModelScope.launch {
            val expenses = expenseRepository.getAll()
            _listOfExpense.value = expenses
        }
    }
}