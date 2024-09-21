package com.testproject.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testproject.databinding.ObjectItemBinding

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ObjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindProduct(product: Product) {
            with(binding) {
                nameTextView.text = product.name
                product.data?.let { productDetails ->
                    bindDetails(productDetails)
                } ?: clearDetails()
            }
        }

        private fun ObjectItemBinding.bindDetails(productDetails: ProductData) {
            colorTextView.text = productDetails.color ?: "N/A"
            generationTextView.text = productDetails.generation ?: "N/A"
            priceTextView.text = productDetails.price?.toString() ?: "N/A"
            capacityTextView.text = productDetails.capacity ?: "N/A"
            cpuModelTextView.text = productDetails.cpuModel ?: "N/A"
            hardDiskTextView.text = productDetails.hardDiskSize?.let { "$it GB" } ?: "N/A"
            screenSizeTextView.text = productDetails.screenSize?.toString() ?: "N/A"
            descriptionTextView.text = productDetails.description ?: "N/A"
            yearTextView.text = productDetails.year?.toString() ?: "N/A"
        }

        private fun ObjectItemBinding.clearDetails() {
            colorTextView.text = "N/A"
            generationTextView.text = "N/A"
            priceTextView.text = "N/A"
            capacityTextView.text = "N/A"
            cpuModelTextView.text = "N/A"
            hardDiskTextView.text = "N/A"
            screenSizeTextView.text = "N/A"
            descriptionTextView.text = "N/A"
            yearTextView.text = "N/A"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ObjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindProduct(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}
