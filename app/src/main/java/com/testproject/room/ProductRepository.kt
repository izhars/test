package com.testproject.room

import android.util.Log
import androidx.lifecycle.LiveData
import com.testproject.ApiService

class ProductRepository(private val apiService: ApiService, private val productDao: ProductDao) {

    val products: LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun refreshProducts() {
        try {
            val response = apiService.getProducts()
            Log.e("wygeydfvdfyc",products.toString())
            productDao.insertProducts(response)
        } catch (e: Exception) {
            // Handle error (e.g., no network connection)
        }
    }
}





