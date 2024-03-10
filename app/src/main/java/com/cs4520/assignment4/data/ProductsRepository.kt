package com.cs4520.assignment4.data

import com.cs4520.assignment4.model.Product

interface ProductsRepository {
    suspend fun insertAll(products: List<Product>, page: Int)
    suspend fun getProductsOnPage(page: Int): List<Product>
    suspend fun deleteProductsOnPage(page: Int)
}