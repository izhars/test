package com.testproject

import android.app.Application
import androidx.room.Room
import com.testproject.room.AppDatabase
import com.testproject.room.ProductRepository

class MyApp : Application() {

    lateinit var repository: ProductRepository
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,
            "product_database"
        ).build()

        val apiService = RetrofitInstance.api
        repository = ProductRepository(apiService, database.productDao())
    }
}

