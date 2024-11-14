package com.felipe.gestaofinanceira.data.repository

import com.felipe.gestaofinanceira.data.datasource.IncomeApiDataSource
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.datasource.model.Income

class IncomeRepository(
    private val incomeApiDataSource: IncomeApiDataSource = IncomeApiDataSource()
) {
    suspend fun get(id: String): Income {
        val result = incomeApiDataSource.get(id)
        return result.getOrThrow()
    }

    suspend fun getAll(): List<Income> {
        val result = incomeApiDataSource.getAll()
        return result.getOrThrow()
    }

    suspend fun create(income: Income): Income {
        val result = incomeApiDataSource.create(income)
        return result.getOrThrow()
    }

    suspend fun delete(id: String): Income {
        val result = incomeApiDataSource.delete(id)
        return result.getOrThrow()
    }

    suspend fun update(id: String, income: Income): Income {
        val result = incomeApiDataSource.update(id, income)
        return result.getOrThrow()
    }
}