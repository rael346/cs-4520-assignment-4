package com.cs4520.assignment4.model

import com.cs4520.assignment4.data.ProductEntity

// I tried the sealed class approach but couldn't get the GSON deserializer
// to parse the json to the correct class, so this just have to do for now
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
