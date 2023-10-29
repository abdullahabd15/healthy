package com.abdullah.kotlintechnicaltest.domain.usecases.contents

import com.abdullah.kotlintechnicaltest.data.repositories.local.ContentLocalRepository
import com.abdullah.kotlintechnicaltest.domain.model.ProductServiceData
import com.abdullah.kotlintechnicaltest.domain.model.mapToData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProductServicesUseCase @Inject constructor(
    private val contentLocalRepository: ContentLocalRepository,
) {
    operator fun invoke(): List<ProductServiceData> =
        contentLocalRepository.getProductServices().map { it.mapToData() }
}