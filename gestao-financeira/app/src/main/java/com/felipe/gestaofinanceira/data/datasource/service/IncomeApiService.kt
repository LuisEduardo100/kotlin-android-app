package com.felipe.gestaofinanceira.data.datasource.service

import com.felipe.gestaofinanceira.data.datasource.model.Income
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface IncomeApiService {
    @GET("/income/{id}")
    suspend fun get(@Path("id") id: String): Response<Income?>

    @GET("/income")
    suspend fun getAll(): Response<List<Income>>

    @POST("/income")
    suspend fun create(@Body income: Income): Response<Income>

    @DELETE("/income/{id}")
    suspend fun delete(@Path("id") id: String): Response<Income>

    @PUT("/income/{id}")
    suspend fun update(@Path("id") id: String, @Body income: Income): Response<Income>
}