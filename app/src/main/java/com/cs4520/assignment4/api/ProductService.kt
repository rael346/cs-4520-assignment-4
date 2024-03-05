package com.cs4520.assignment4.api

import com.cs4520.assignment4.model.Product
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("prod")
    suspend fun getProducts(@Query("page") pageNum: Int): Response<List<Product>>
}