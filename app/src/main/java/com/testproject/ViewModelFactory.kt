package com.testproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testproject.room.ProductRepository
import com.testproject.room.ProductViewModel

class ViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

