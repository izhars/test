package com.testproject

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.testproject.room.ProductAdapter
import com.testproject.room.ProductViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeLayout: SwipeRefreshLayout

    private val productViewModel: ProductViewModel by viewModels {
        ViewModelFactory((application as MyApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupObservers()

        swipeLayout.setOnRefreshListener {
            loadProductData()
        }
    }

    private fun setupUI() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        swipeLayout = findViewById(R.id.swipeRefreshLayout)

        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE
    }

    private fun setupObservers() {
        productViewModel.products.observe(this) { productList ->
            progressBar.visibility = View.GONE
            swipeLayout.isRefreshing = false

            if (!productList.isNullOrEmpty()) {
                productAdapter = ProductAdapter(productList)
                recyclerView.adapter = productAdapter
            }
        }

        loadProductData()
    }

    private fun loadProductData() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            if (!swipeLayout.isRefreshing) {
                progressBar.visibility = View.VISIBLE
            }

            productViewModel.refreshProducts(this)
            swipeLayout.isRefreshing = true
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            swipeLayout.isRefreshing = false
        }
    }
}
