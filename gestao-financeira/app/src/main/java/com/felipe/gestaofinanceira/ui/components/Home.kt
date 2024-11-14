package com.felipe.gestaofinanceira.ui.components

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navControl: NavHostController) {
    var billHandler by remember { mutableStateOf("Income") }

    val btnModifier = Modifier
        .padding(5.dp, 12.dp)
        .width(180.dp)

    NavHost(navController = navControl, startDestination = "Home") {
        composable(route = "Home") {
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
                            if (billHandler == "Income") IncomeList() else ExpenseList()
                        }
                    }
                }
            }
        }
        composable(route = "NewBilling") {
            BillingForm(navControl)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navControl = rememberNavController()
    Home(navControl)
}