package com.cs4520.assignment4.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs4520.assignment4.data.ProductsRepository
import com.cs4520.assignment4.model.Product
import kotlinx.coroutines.launch
import kotlin.Exception

class ProductListViewModel(private val productsRepository: ProductsRepository): ViewModel() {
    enum class State {
        LOADING, SUCCESS, FAIL
    }

    val state: MutableLiveData<State> = MutableLiveData<State>(State.LOADING)
    var errorMessage: String? = null
    val products: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    val currentPage: MutableLiveData<Int> = MutableLiveData(1)

    init {
        getProducts(1)
    }
    private fun getProducts(page: Int) {
        viewModelScope.launch {
            try {
                state.value = State.LOADING
                products.value = productsRepository.getProductsOnPage(page)
                state.value = State.SUCCESS
            } catch (e: Exception) {
                state.value = State.FAIL
                errorMessage = e.message
            }
        }
    }

    fun incrementPage() {
        currentPage.value = currentPage.value!! + 1
        getProducts(currentPage.value!!)
    }

    fun decrementPage() {
        currentPage.value = currentPage.value!! - 1
        getProducts(currentPage.value!!)
    }
}