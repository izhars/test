package com.testproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testproject.room.ProductAdapter
import com.testproject.room.ProductViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var progressBar: ProgressBar

    // ViewModel for the Activity
    private val viewModel: ProductViewModel by viewModels {
        ViewModelFactory((application as MyApp).repository) // Access the repository from MyApp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.products.observe(this) { products ->
            if (products != null && products.isNotEmpty()) {
                Log.e("qdbuywqav",products.toString())
                adapter = ProductAdapter(products)
                recyclerView.adapter = adapter
            } else {
                // Handle empty or null products
            }
            progressBar.visibility = View.GONE
        }

        viewModel.refreshProducts()
        progressBar.visibility = View.VISIBLE
    }
}



