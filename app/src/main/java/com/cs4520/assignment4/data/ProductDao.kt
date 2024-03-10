package com.cs4520.assignment4.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cs4520.assignment4.model.Product

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: ProductEntity)

    @Update
    suspend fun update(product: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)

    @Insert
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT name, type, expiryDate, price FROM products WHERE page = :page")
    fun getProductsOnPage(page: Int): List<Product>

    @Query("DELETE FROM products WHERE page = :page")
    fun deleteProductsOnPage(page: Int)
}