package com.testproject.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testproject.databinding.ObjectItemBinding

class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ObjectItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.nameTextView.text = product.name

            val productData = product.data
            if (productData != null) {
                binding.colorTextView.text = productData.color?.let { "Colour: $it" } ?:"Colour: N/A"
                binding.generationTextView.text = productData.generation?.let { "Generation: $it" } ?:"Generation: N/A"
                binding.priceTextView.text = productData.price?.let { "Price: $it" } ?: "Price: N/A"
                binding.capacityTextView.text = productData.capacity?.let { "Capacity: $it " } ?: "Capacity: N/A"
                binding.cpuModelTextView.text = productData.cpuModel ?.let { "CPU Model: $it" } ?: "CPU Model: N/A"
                binding.hardDiskTextView.text = productData.hardDiskSize?.let { "Hard Disk: $it GB" } ?: "Hard Disk: N/A"
                binding.screenSizeTextView.text = productData.screenSize?.let { "Screen Size: $it " } ?: "Screen Size: N/A"
                binding.descriptionTextView.text = productData.description?.let { "Description: $it" } ?:"Description: N/A"

                if (productData.year != null) {
                    binding.yearTextView.text = "Year: ${productData.year}"
                    binding.yearTextView.visibility = View.VISIBLE
                } else {
                    binding.yearTextView.visibility = View.GONE
                }
            } else {

                binding.colorTextView.text = "Color: N/A"
                binding.generationTextView.text = "Generation: N/A"
                binding.priceTextView.text = "Price: N/A"
                binding.capacityTextView.text = "Capacity: N/A"
                binding.cpuModelTextView.text = "CPU Model: N/A"
                binding.hardDiskTextView.text = "Hard Disk: N/A"
                binding.screenSizeTextView.text = "Screen Size: N/A"
                binding.descriptionTextView.text= "Description: N/A"
                binding.yearTextView.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ObjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}