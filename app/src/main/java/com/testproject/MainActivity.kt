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
    private lateinit var adapter: ProductAdapter
    private lateinit var progBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout // Add this line

    private val viewModel: ProductViewModel by viewModels {
        ViewModelFactory((application as MyApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progBar = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        recyclerView.layoutManager = LinearLayoutManager(this)

        progBar.visibility = View.VISIBLE
        viewModel.products.observe(this) { products ->
            progBar.visibility = View.GONE
            if (products != null && products.isNotEmpty()) {
                adapter = ProductAdapter(products)
                recyclerView.adapter = adapter
            } else {

            }
            progBar.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
        }
        refreshData()
        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }
    }

    private fun refreshData() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            if (!swipeRefreshLayout.isRefreshing) {
                progBar.visibility = View.VISIBLE
            }

            viewModel.refreshProducts(this)
            swipeRefreshLayout.isRefreshing = true
        } else {
            Toast.makeText(this, "Internet is not available.", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}




