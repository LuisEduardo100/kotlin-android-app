package com.felipe.gestaofinanceira.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.datasource.model.Income
import com.felipe.gestaofinanceira.data.repository.ExpenseRepository
import com.felipe.gestaofinanceira.data.repository.IncomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class BillingFormViewModel(
    private val  incomeRepository: IncomeRepository = IncomeRepository(),
    private val  expenseRepository: ExpenseRepository = ExpenseRepository()
): ViewModel() {

    private var _mainTitle = MutableStateFlow("Adicionar nova atividade")
    val mainTitle = _mainTitle.asStateFlow()

    private var _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private var _value = MutableStateFlow(0.00)
    val value = _value.asStateFlow()

    private var _expenseCheckbox = MutableStateFlow(false)
    val expenseCheckbox = _expenseCheckbox.asStateFlow()

    private var _incomeCheckbox = MutableStateFlow(false)
    val incomeCheckbox = _incomeCheckbox.asStateFlow()

    private var _buttonMessage = MutableStateFlow("Submit")
    val buttonMessage = _buttonMessage.asStateFlow()

    private var alreadyFilledInfo = false

    fun fillInfo(income: Income? = null, expense: Expense? = null) {
        if (alreadyFilledInfo) {
            return
        }

        if (income != null) {
            if (income.title == "") return
            changeTitleValue(income.title)
            changeValueValue(income.value)
            changeIncomeCheckboxValue()
            changeMainTitleValue("Editar income")
            changeBtnMessage("Save income")
            alreadyFilledInfo = true
        }

        if (expense != null) {
            if (expense.title == "") return
            changeTitleValue(expense.title)
            changeValueValue(expense.value)
            changeExpenseCheckboxValue()
            changeMainTitleValue("Editar expense")
            changeBtnMessage("Save expense")
            alreadyFilledInfo = true
        }
    }

    private fun changeMainTitleValue(newMainTitle: String) {
        _mainTitle.value = newMainTitle
    }

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

    private fun changeBtnMessage(btnMessage: String) {
        _buttonMessage.value = btnMessage
    }

    fun deleteActivity(id: String, isIncome: Boolean, navControl: NavController) {
        viewModelScope.launch {
            if (isIncome) {
                incomeRepository.delete(id)
            } else {
                expenseRepository.delete(id)
            }
            delay(500)
            navControl.navigate("Home")
        }
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

    fun onSubmitEdit(navController: NavController, expenseId: String? = null, incomeId: String? = null) {
        viewModelScope.launch {
            if ((_title.value.isNotEmpty() && _value.value != 0.00)) {
                if (expenseId != null) {
                    val newExpense = Expense(
                        expenseId,
                        _title.value,
                        Date(),
                        _value.value,
                    )
                    expenseRepository.update(expenseId, newExpense)
                    navController.navigate("Home")
                } else if (incomeId != null) {
                    val newIncome = Income(
                        incomeId,
                        _title.value,
                        Date(),
                        _value.value,
                    )
                    incomeRepository.update(incomeId, newIncome)
                    navController.navigate("Home")
                }
            }
        }
    }
}