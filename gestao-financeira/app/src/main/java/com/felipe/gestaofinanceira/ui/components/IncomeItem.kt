package com.felipe.gestaofinanceira.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.felipe.gestaofinanceira.data.datasource.model.Income

import java.util.Date

@Composable
fun IncomeItem(income: Income, navController: NavController) {
    Spacer(
        modifier = Modifier.height(16.dp)
    )
    Row(
        modifier = Modifier
            .padding(12.dp, 6.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color(218, 226, 255))
            .clickable {
                navController.navigate("EditIncome/${income.id}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 15.dp, height = 70.dp)
                .clip(RectangleShape)
                .background(Color(77, 188, 77))
        )
        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = income.title,
                fontSize = 24.sp,
                fontWeight = FontWeight(1000),

                )
            Text(
                fontSize = 12.sp,
                text = "${income.date}"
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 70.dp)
                .clip(RectangleShape)
                .background(Color(77, 188, 77)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "R$${"%.2f".format(income.value)}",
                fontWeight = FontWeight(1000),
                modifier = Modifier.padding(12.dp)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IncomeItemPreview() {
    IncomeItem(
        Income(
            id = "1",
            title = "Bico",
            date = Date(),
            value = 20.00
        ),
        rememberNavController()
    )
}