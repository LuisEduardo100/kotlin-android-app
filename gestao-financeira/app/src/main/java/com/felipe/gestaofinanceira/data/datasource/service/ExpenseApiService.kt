package com.felipe.gestaofinanceira.data.datasource.service

import com.felipe.gestaofinanceira.data.datasource.model.Expense
import com.felipe.gestaofinanceira.data.datasource.model.Income
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ExpenseApiService {
    @GET("/expenses/{id}")
    suspend fun get(@Path("id") id: String): Response<Expense?>

    @GET("/expenses")
    suspend fun getAll(): Response<List<Expense>>

    @POST("/expenses")
    suspend fun create(@Body expense: Expense): Response<Expense>

    @DELETE("/expenses/{id}")
    suspend fun delete(@Path("id") id: String): Response<Expense>

    @PUT("/expenses/{id}")
    suspend fun update(@Path("id") id: String, @Body expense: Expense): Response<Expense>
}