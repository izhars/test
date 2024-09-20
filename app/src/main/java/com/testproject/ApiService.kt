package com.testproject

import com.testproject.room.Product
import retrofit2.http.GET

interface ApiService {
    @GET("objects")
    suspend fun getProducts(): List<Product>
}