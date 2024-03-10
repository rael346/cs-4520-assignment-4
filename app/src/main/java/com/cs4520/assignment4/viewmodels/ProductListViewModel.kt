package com.cs4520.assignment4.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs4520.assignment4.data.ProductsRepository
import com.cs4520.assignment4.model.Product
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.Exception

class ProductListViewModel(private val productsRepository: ProductsRepository): ViewModel() {
    enum class State {
        LOADING, SUCCESS, FAIL
    }

    val state: MutableLiveData<State> = MutableLiveData<State>(State.LOADING)
    var errorMessage: String? = null
    val products: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                products.value = productsRepository.getProductsOnPage(1)
                state.value = State.SUCCESS
            } catch (e: Exception) {
                state.value = State.FAIL
                errorMessage = e.message
            }
        }
    }
}