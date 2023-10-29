package com.abdullah.kotlintechnicaltest.domain.model

import com.abdullah.kotlintechnicaltest.data.model.contents.TypeService

data class TypeServiceData(
    val id: Int?,
    val name: String?
)

fun TypeService.mapToData(): TypeServiceData = TypeServiceData(id, name)
