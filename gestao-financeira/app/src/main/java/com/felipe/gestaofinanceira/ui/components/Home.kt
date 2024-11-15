package com.felipe.gestaofinanceira.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.felipe.gestaofinanceira.data.repository.IncomeRepository
import com.felipe.gestaofinanceira.ui.viewmodel.ExpenseListViewModel
import com.felipe.gestaofinanceira.ui.viewmodel.IncomeListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navControl: NavHostController,
    incomeViewModel: IncomeListViewModel = viewModel(),
    expenseViewModel: ExpenseListViewModel = viewModel()
) {
    var billHandler by remember { mutableStateOf("Income") }

    val btnModifier = Modifier
        .padding(5.dp, 12.dp)
        .width(180.dp)

    NavHost(navController = navControl, startDestination = "Home") {
        composable(route = "Home") {
            CustomScaffold(
                title = "Financial Gaps",
                onNavigateHome = { navControl.navigate("Home") },
                onNavigateNewBilling = { navControl.navigate("NewBilling") },
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    Column(
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    billHandler = "Income"
                                },
                                modifier = btnModifier,
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    if (billHandler == "Income") MaterialTheme.colorScheme.primary else Color(
                                        99,
                                        110,
                                        137
                                    )
                                )
                            ) {
                                Text(text = "INCOME")
                            }
                            Button(
                                onClick = {
                                    billHandler = "Expense"
                                },
                                modifier = btnModifier,
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    if (billHandler == "Expense") MaterialTheme.colorScheme.primary else Color(
                                        99,
                                        110,
                                        137
                                    )
                                )
                            ) {
                                Text(text = "EXPENSES")
                            }
                        }
                        Box(
                            modifier = Modifier
                                .width(375.dp)
                                .height(630.dp)
                                .background(color = Color(73, 93, 146))
                                .align(Alignment.CenterHorizontally)
                        ) {
                            if (billHandler == "Income") IncomeList(navController = navControl) else ExpenseList(navController = navControl)
                        }
                    }
                }
            }
        }
        composable(route = "NewBilling") {
            BillingForm(navControl)
        }
        composable(
            route = "EditIncome/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id")!!
            incomeViewModel.getIncome(id)

            val income = incomeViewModel.income.collectAsState().value
            BillingForm(navControl, income = income)
        }
        composable(
            route = "EditExpense/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id")!!
            expenseViewModel.getExpense(id)

            val expense = expenseViewModel.expense.collectAsState().value
            BillingForm(navControl, expense = expense)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navControl = rememberNavController()
    Home(navControl)
}