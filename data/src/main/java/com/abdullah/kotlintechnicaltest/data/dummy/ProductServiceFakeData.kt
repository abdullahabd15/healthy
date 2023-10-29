package com.abdullah.kotlintechnicaltest.data.dummy

import com.abdullah.kotlintechnicaltest.data.model.contents.ProductService

object ProductServiceFakeData {
    val data = listOf(
        ProductService(
            id = 1,
            name = "All Product"
        ),
        ProductService(
            id = 2,
            name = "Layanan Kesehatan"
        ),
        ProductService(
            id = 3,
            name = "Alat Kesehatan"
        ),
        ProductService(
            id = 4,
            name = "Covid Test"
        ),
    )
}