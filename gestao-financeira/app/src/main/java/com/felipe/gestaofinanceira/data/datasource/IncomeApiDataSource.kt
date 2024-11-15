package com.felipe.gestaofinanceira.data.datasource

import com.felipe.gestaofinanceira.common.RetrofitUtil
import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.datasource.model.Income
import com.felipe.gestaofinanceira.data.datasource.service.IncomeApiService

class IncomeApiDataSource(
    private val incomeApiService: IncomeApiService = RetrofitUtil.getIncomeApiService()
) {
    suspend fun get(id: String): Result<Income?> {
        val response = incomeApiService.get(id)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("GET ERROR"))
    }

    suspend fun getAll(): Result<List<Income>> {
        val response = incomeApiService.getAll()
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("GET ALL ERROR"))
    }

    suspend fun create(income: Income): Result<Income> {
        val response = incomeApiService.create(income)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("CREATE ERROR"))
    }

    suspend fun delete(id: String): Result<Income> {
        val response = incomeApiService.delete(id)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("DELETE ERROR"))
    }

    suspend fun update(id: String, income: Income): Result<Income> {
        val response = incomeApiService.update(id, income)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        }
        return Result.failure(Exception("UPDATE ERROR"))
    }
}