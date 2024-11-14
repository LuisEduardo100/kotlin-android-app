package com.felipe.gestaofinanceira.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.datasource.model.Income
import com.felipe.gestaofinanceira.data.repository.ExpenseRepository
import com.felipe.gestaofinanceira.data.repository.IncomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class BillingFormViewModel(
    private val  incomeRepository: IncomeRepository = IncomeRepository(),
    private val  expenseRepository: ExpenseRepository = ExpenseRepository()
): ViewModel() {
    private var _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private var _value = MutableStateFlow(0.00)
    val value = _value.asStateFlow()

    private var _expenseCheckbox = MutableStateFlow(false)
    val expenseCheckbox = _expenseCheckbox.asStateFlow()

    private var _incomeCheckbox = MutableStateFlow(false)
    val incomeCheckbox = _incomeCheckbox.asStateFlow()

    fun changeTitleValue(newTitle: String) {
        _title.value = newTitle
    }
    fun changeValueValue(newValue: Double) {
        _value.value = newValue
    }

    fun changeIncomeCheckboxValue() {
        _incomeCheckbox.value = !_incomeCheckbox.value
    }

    fun changeExpenseCheckboxValue() {
        _expenseCheckbox.value = !_expenseCheckbox.value
    }

    fun onSubmit(navController: NavController) {
        viewModelScope.launch {
            if ((_title.value.isNotEmpty() && _value.value != 0.00)) {
                if (_expenseCheckbox.value) {
                    val newExpense = Expense(
                        UUID.randomUUID().toString(),
                        _title.value,
                        Date(),
                        _value.value,
                    )
                    expenseRepository.create(newExpense)
                    navController.navigate("Home")
                } else if (_incomeCheckbox.value) {
                    val newIncome = Income(
                        UUID.randomUUID().toString(),
                        _title.value,
                        Date(),
                        _value.value,
                    )
                    incomeRepository.create(newIncome)
                    navController.navigate("Home")
                }
            }
        }
    }
}