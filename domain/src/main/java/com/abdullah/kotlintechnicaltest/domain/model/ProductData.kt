package com.abdullah.kotlintechnicaltest.domain.model

import com.abdullah.kotlintechnicaltest.data.model.contents.Product

data class ProductData(
    val id: Int?,
    val name: String?,
    val price: String?,
    val status: String?,
    val rating: Float?
)

fun Product.mapToData(): ProductData = ProductData(id, name, price, status, rating)
