package com.testproject.room

import com.google.gson.annotations.SerializedName
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: String,
    val name: String,
    @Embedded val data: ProductData?
)

data class ProductData(
    @SerializedName(value = "color", alternate = ["Color", "Strap Colour"])
    val color: String?,

    @SerializedName(value = "capacity", alternate = ["Capacity", "capacity GB"])
    val capacity: String?,

    @SerializedName(value = "price", alternate = ["Price"])
    val price: Double?,

    @SerializedName("Description")
    val description: String?,

    @SerializedName("year")
    val year: Int?,

    @SerializedName(value = "generation", alternate = ["Generation"])
    val generation: String?,

    @SerializedName("CPU model")
    val cpuModel: String?,

    @SerializedName(value = "hardDiskSize", alternate = ["Hard disk size"])
    val hardDiskSize: String?,

    @SerializedName(value = "screenSize", alternate = ["Screen size"])
    val screenSize: Double?,

    )


