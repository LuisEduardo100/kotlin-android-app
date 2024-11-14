package com.felipe.gestaofinanceira.ui.components

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.felipe.gestaofinanceira.ui.viewmodel.BillingFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillingForm(
    navControl: NavController,
    billingFormViewModel: BillingFormViewModel = viewModel()
) {
    val formModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Financial Gaps")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        navControl.navigate("Home")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    }
                    IconButton(onClick = {
                        navControl.navigate("NewBilling")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Adicionar Despesa/Renda"
                        )
                    }
                    IconButton(onClick = { /* Abrir perfil do usuário */ }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Perfil do Usuário"
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Text(
                text = "Adicionar nova atividade",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = formModifier
            )
            TextField(
                value = billingFormViewModel.title.collectAsState().value,
                onValueChange = { billingFormViewModel.changeTitleValue(it) },
                label = { Text("Title: ") },
                modifier = formModifier
            )
            TextField(
                value = billingFormViewModel.value.collectAsState().value.toString(),
                onValueChange = {
                    val newDoubleValue = it.toDoubleOrNull()
                    if (newDoubleValue != null) {
                        billingFormViewModel.changeValueValue(newDoubleValue)
                    }
                },
                label = { Text("Value: ") },
                modifier = formModifier
            )
            Row (
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
                onClick = { billingFormViewModel.onSubmit(navControl) },
                modifier = formModifier
            ) {
                Text("Submit")
            }
        }
    }
}