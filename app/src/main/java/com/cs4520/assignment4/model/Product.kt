package com.cs4520.assignment4.model

import com.cs4520.assignment4.data.ProductEntity

data class Product (
    val name: String,
    val type: String,
    val expiryDate: String?,
    val price: Double,
) {
    fun toEntity(page: Int): ProductEntity {
        return ProductEntity(
            name = name,
            type = type,
            expiryDate = expiryDate,
            price = price,
            page = page
        )
    }
}
