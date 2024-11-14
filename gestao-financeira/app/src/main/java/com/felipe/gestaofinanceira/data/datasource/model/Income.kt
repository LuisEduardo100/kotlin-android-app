package com.felipe.gestaofinanceira.data.datasource.model

import java.util.Date

data class Income(
    val id: String,
    val title: String,
    val date: Date,
    val value: Double
)