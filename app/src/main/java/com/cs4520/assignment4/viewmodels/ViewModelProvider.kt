package com.cs4520.assignment4.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cs4520.assignment4.MainApplication

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            LoginViewModel()
        }

        initializer {
            ProductListViewModel(application().container.productsRepository)
        }
    }
}

fun CreationExtras.application(): MainApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)