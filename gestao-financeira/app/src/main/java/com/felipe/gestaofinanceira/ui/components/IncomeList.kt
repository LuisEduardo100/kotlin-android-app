package com.felipe.gestaofinanceira.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.felipe.gestaofinanceira.ui.viewmodel.IncomeListViewModel

@Composable
fun IncomeList(
    incomeListViewModel: IncomeListViewModel = viewModel()

) {

    incomeListViewModel.getIncomeList()
    val incomes = incomeListViewModel.listOfIncome.collectAsState().value

    LazyColumn() {
        items(incomes) { income ->
            IncomeItem(income)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun IncomeListPreview() {
    IncomeList()
}