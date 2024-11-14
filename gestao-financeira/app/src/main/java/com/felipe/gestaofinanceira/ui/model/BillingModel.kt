package com.felipe.gestaofinanceira.ui.model

import java.util.Date

data class BillingModel (
    val id: String,
    val title: String,
    val date: Date,
    val value: Double
)