package com.testproject.room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.testproject.ApiService
import com.testproject.NetworkUtils

class ProductRepository(private val apiService: ApiService, private val productDao: ProductDao) {

    val products: LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun refreshProducts(context: Context) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            try {
                val response = apiService.getProducts()
                productDao.insertProducts(response)
            } catch (e: Exception) {
                Log.e("ProductRepository", "Error: ${e.message}")
            }
        }
    }
}






