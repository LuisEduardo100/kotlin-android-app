package com.felipe.gestaofinanceira.data.datasource

import com.felipe.gestaofinanceira.common.RetrofitUtil
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.datasource.model.Income
import com.felipe.gestaofinanceira.data.datasource.service.IncomeApiService

class IncomeApiDataSource(
    private val incomeApiService: IncomeApiService = RetrofitUtil.getIncomeApiService()
) {
    suspend fun get(id: String): Result<Income> {
        val response = incomeApiService.get(id)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to get Expense from API"))
    }

    suspend fun getAll(): Result<List<Income>> {
        val response = incomeApiService.getAll()
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to get Expenses from API"))
    }

    suspend fun create(income: Income): Result<Income> {
        val response = incomeApiService.create(income)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to create Expense from API"))
    }

    suspend fun delete(id: String): Result<Income> {
        val response = incomeApiService.delete(id)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to delete Expense from API"))
    }

    suspend fun update(id: String, income: Income): Result<Income> {
        val response = incomeApiService.update(id, income)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to update Expense from API"))
    }
}