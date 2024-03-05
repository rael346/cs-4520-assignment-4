package com.cs4520.assignment4.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs4520.assignment4.api.RetrofitInstance
import com.cs4520.assignment4.model.Product
import kotlinx.coroutines.launch
import java.io.IOException

class ProductListViewModel: ViewModel() {
    enum class State {
        LOADING, SUCCESS, FAIL
    }

    val state: MutableLiveData<State> = MutableLiveData<State>(State.LOADING)
    val products: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()

    fun fetchProducts() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.productService.getProducts(0)
            } catch (e: IOException) {
                Log.e("ProductList", "IOException, you might not have internet connection")
                state.value = State.FAIL
                return@launch
            }

            if(response.isSuccessful && response.body() != null) {
                Log.i("ProductList", response.body().toString())
                products.value = response.body()
                state.value = State.SUCCESS
            } else {
                Log.e("ProductList", "Response not successful")
                state.value = State.FAIL
            }
        }
    }
}