package com.felipe.gestaofinanceira.ui.components


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.felipe.gestaofinanceira.ui.viewmodel.ExpenseListViewModel

@Composable
fun ExpenseList(
    expenseListViewModel: ExpenseListViewModel = viewModel()
) {

    expenseListViewModel.getExpenseList()
    val expenses = expenseListViewModel.listOfExpense.collectAsState().value

    LazyColumn() {
        items(expenses) { expense ->
            ExpenseItem(expense)
        }
    }

}
