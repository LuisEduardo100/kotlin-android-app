package com.felipe.gestaofinanceira.common

import com.felipe.gestaofinanceira.data.datasource.service.ExpenseApiService
import com.felipe.gestaofinanceira.data.datasource.service.IncomeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    fun getExpenseApiService(): ExpenseApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ExpenseApiService::class.java)
    }

    fun getIncomeApiService(): IncomeApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(IncomeApiService::class.java)
    }
}