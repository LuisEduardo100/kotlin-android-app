package com.felipe.gestaofinanceira.data.datasource

import com.felipe.gestaofinanceira.common.RetrofitUtil
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.datasource.service.ExpenseApiService

class ExpenseApiDataSource(
    private val expenseApiService: ExpenseApiService = RetrofitUtil.getExpenseApiService()
) {
    suspend fun get(id: String): Result<Expense> {
        val response = expenseApiService.get(id)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to get Expense from API"))
    }

    suspend fun getAll(): Result<List<Expense>> {
        val response = expenseApiService.getAll()
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to get Expenses from API"))
    }

    suspend fun create(expense: Expense): Result<Expense> {
        val response = expenseApiService.create(expense)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to create Expense from API"))
    }

    suspend fun delete(id: String): Result<Expense> {
        val response = expenseApiService.delete(id)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to delete Expense from API"))
    }

    suspend fun update(id: String, expense: Expense): Result<Expense> {
        val response = expenseApiService.update(id, expense)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("Failed to update Expense from API"))
    }
}