package com.abdullah.kotlintechnicaltest.domain.model

import com.abdullah.kotlintechnicaltest.data.model.contents.ProductService

data class ProductServiceData(
    val id: Int?,
    val name: String?,
)

fun ProductService.mapToData(): ProductServiceData = ProductServiceData(id, name)
