package com.cs4520.assignment4.model

sealed class Product {
    abstract val name: String
    abstract val date: String?
    abstract val price: Int

    data class Equipment(override val name: String, override val date: String?, override val price: Int) : Product() {

    }

    data class Food(override val name: String, override val date: String?, override val price: Int) : Product() {

    }
}
fun categorize(name: String, productType: String, date: String?, price: Int): Product {
    if (productType == "Equipment") {
        return Product.Equipment(name, date, price)
    }

    if (productType == "Food") {
        return Product.Food(name, date, price)
    }

    throw Error("Product Type $productType is not supported")
}
