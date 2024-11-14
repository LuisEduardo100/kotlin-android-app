package com.felipe.gestaofinanceira.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipe.gestaofinanceira.data.datasource.model.Income
import com.felipe.gestaofinanceira.data.repository.IncomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IncomeListViewModel(
    private val incomeRepository: IncomeRepository = IncomeRepository()
): ViewModel() {

    private var _listOfIncome = MutableStateFlow(listOf<Income>())
    val listOfIncome = _listOfIncome.asStateFlow()

    fun getIncomeList() {
        viewModelScope.launch {
            val incomes = incomeRepository.getAll()
            _listOfIncome.value = incomes
        }
    }
}