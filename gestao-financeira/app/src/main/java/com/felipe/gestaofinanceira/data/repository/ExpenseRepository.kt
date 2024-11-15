package com.felipe.gestaofinanceira.data.repository

import com.felipe.gestaofinanceira.data.datasource.ExpenseApiDataSource
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import kotlin.math.exp

class ExpenseRepository(
    private val expenseApiDataSource: ExpenseApiDataSource = ExpenseApiDataSource()
) {
    suspend fun get(id: String): Expense? {
        val result = expenseApiDataSource.get(id)
        return result.getOrNull()
    }

    suspend fun getAll(): List<Expense> {
        val result = expenseApiDataSource.getAll()
        return result.getOrThrow()
    }

    suspend fun create(expense: Expense): Expense {
        val result = expenseApiDataSource.create(expense)
        return result.getOrThrow()
    }

    suspend fun delete(id: String): Expense {
        val result = expenseApiDataSource.delete(id)
        return result.getOrThrow()
    }

    suspend fun update(id: String, expense: Expense): Expense {
        val result = expenseApiDataSource.update(id, expense)
        return result.getOrThrow()
    }
}