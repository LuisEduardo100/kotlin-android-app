package com.felipe.gestaofinanceira.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.datasource.model.Income
import com.felipe.gestaofinanceira.data.repository.IncomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

class IncomeListViewModel(
    private val incomeRepository: IncomeRepository = IncomeRepository()
): ViewModel() {

    private var _listOfIncome = MutableStateFlow(listOf<Income>())
    val listOfIncome = _listOfIncome.asStateFlow()

    private var _income = MutableStateFlow(Income("", "", Date(), 0.0))
    val income = _income.asStateFlow()

    fun getIncome(id: String) {
        viewModelScope.launch {
            val income = incomeRepository.get(id)
            if (income != null) {
                _income.value = income
            }
        }
    }

    fun getIncomeList() {
        viewModelScope.launch {
            val incomes = incomeRepository.getAll()
            _listOfIncome.value = incomes
        }
    }
}