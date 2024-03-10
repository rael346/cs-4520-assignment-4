package com.cs4520.assignment4.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cs4520.assignment4.Util
import com.cs4520.assignment4.api.RetrofitInstance
import com.cs4520.assignment4.model.Product
import com.cs4520.assignment4.viewmodels.ProductListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OfflineProductsRepository(private val productDao: ProductDao, private val context: Context): ProductsRepository {
    override suspend fun insertAll(products: List<Product>, page: Int) {
        val entities = products.map { it.toEntity(page) }
        productDao.insertAll(entities)
    }

    override suspend fun getProductsOnPage(page: Int): List<Product> {
        return if (Util.isInternetAvailable(context)) {
            val response = RetrofitInstance.productService.getProducts(0)
            if (response.isSuccessful && response.body() != null) {
                productDao.insertAll(response.body()!!.map { it.toEntity(page)})
                response.body()!!
            } else {
                throw Exception(response.message())
            }
        } else {
            productDao.getProductsOnPage(page)
        }
    }

    override suspend fun deleteProductsOnPage(page: Int) {
        productDao.deleteProductsOnPage(page)
    }
}