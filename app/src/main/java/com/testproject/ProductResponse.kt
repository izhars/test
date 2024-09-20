package com.testproject

data class ProductResponse(
    val id: String,
    val name: String,
    val data: Data?
)

data class Data(
    val color: String?,
    val capacity: String?,
    val price: Double?
)
