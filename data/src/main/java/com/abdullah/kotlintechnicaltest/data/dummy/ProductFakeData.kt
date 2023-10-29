package com.abdullah.kotlintechnicaltest.data.dummy

import com.abdullah.kotlintechnicaltest.data.model.contents.Product

object ProductFakeData {
    val data = listOf(
        Product(
            id = 1,
            name = "Mikroskop",
            price = "12.000.000",
            status = "Ready Stock",
            rating = 4.9f
        ),
        Product(
            id = 2,
            name = "Microwave",
            price = "824.000",
            status = "Ready Stock",
            rating = 5f
        )
    )
}