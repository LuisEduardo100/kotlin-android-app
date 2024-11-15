package com.felipe.gestaofinanceira.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.datasource.model.Income
import com.felipe.gestaofinanceira.ui.viewmodel.BillingFormViewModel
import kotlinx.coroutines.delay

@Composable
fun BillingForm(
    navControl: NavController,
    expense: Expense? = null,
    income: Income? = null,
    billingFormViewModel: BillingFormViewModel = viewModel()
) {
    val formModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp)

    if (income != null) {
        billingFormViewModel.fillInfo(income = income)
    }

    if (expense != null) {
        billingFormViewModel.fillInfo(expense = expense)
    }

    CustomScaffold(
        title = "Financial Gaps",
        onNavigateHome = { navControl.navigate("Home") },
        onNavigateNewBilling = { navControl.navigate("NewBilling") },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Text(
                text = billingFormViewModel.mainTitle.collectAsState().value,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = formModifier
            )
            TextField(
                value = billingFormViewModel.title.collectAsState().value,
                onValueChange = { billingFormViewModel.changeTitleValue(it) },
                label = { Text("Title: ") },
                modifier = formModifier,
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = billingFormViewModel.value.collectAsState().value.toString(),
                onValueChange = {
                    /*
                    * -Luís:
                    * Tem uma problema nessa função:
                    * Dificuldade em trabalhar com input do tipo double.
                    * O value armazenado no viewModel não é apagado e o valor
                    * escrito pelo usuário é sobrescrito com 0.00 no viewModel
                    * */

                    val newDoubleValue = it.toDoubleOrNull()
                    if (newDoubleValue != null) {
                        billingFormViewModel.changeValueValue(newDoubleValue)
                    }
                },
                label = { Text("Value: ") },
                modifier = formModifier,
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent, // Remove o indicador de foco padrão
                    unfocusedIndicatorColor = Color.Transparent // Remove o indicador quando não focado
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = formModifier
            ) {
                Checkbox(
                    checked = billingFormViewModel.expenseCheckbox.collectAsState().value,
                    onCheckedChange = { billingFormViewModel.changeExpenseCheckboxValue() }
                )
                Text("Expense")
                Checkbox(
                    checked = billingFormViewModel.incomeCheckbox.collectAsState().value,
                    onCheckedChange = { billingFormViewModel.changeIncomeCheckboxValue() }
                )
                Text("Income")
            }
            Button(
                onClick = {
                    if (expense != null) {
                        billingFormViewModel.onSubmitEdit(
                            navControl,
                            expenseId = expense.id
                        )
                    } else if (income != null) {
                        billingFormViewModel.onSubmitEdit(
                            navControl,
                            incomeId = income.id
                        )
                    } else {
                        billingFormViewModel.onSubmit(navControl)
                    }
                },
                modifier = formModifier
            ) {
                Text(billingFormViewModel.buttonMessage.collectAsState().value)
            }
            if (expense != null) {
                Button(
                    onClick = {
                        billingFormViewModel.deleteActivity(expense.id, isIncome = false, navControl)

                    },
                    modifier = formModifier
                ) {
                    Text("Excluir expense")
                }
            }
            if (income != null) {
                Button(
                    onClick = {
                        billingFormViewModel.deleteActivity(income.id, isIncome = true, navControl)
                    },
                    modifier = formModifier
                ) {
                    Text("Excluir income")
                }
            }
        }
    }
}