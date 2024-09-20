package com.testproject.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val products: LiveData<List<Product>> = repository.products

    fun refreshProducts(context: Context) {
        viewModelScope.launch {
            repository.refreshProducts(context)
        }
    }
}





