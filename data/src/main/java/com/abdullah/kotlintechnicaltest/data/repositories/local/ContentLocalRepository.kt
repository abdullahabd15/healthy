package com.abdullah.kotlintechnicaltest.data.repositories.local

import com.abdullah.kotlintechnicaltest.data.dummy.MedicalFacilityFakeData
import com.abdullah.kotlintechnicaltest.data.dummy.ProductFakeData
import com.abdullah.kotlintechnicaltest.data.dummy.ProductServiceFakeData
import com.abdullah.kotlintechnicaltest.data.dummy.TypeServiceFakeData
import com.abdullah.kotlintechnicaltest.data.model.contents.MedicalFacility
import com.abdullah.kotlintechnicaltest.data.model.contents.Product
import com.abdullah.kotlintechnicaltest.data.model.contents.ProductService
import com.abdullah.kotlintechnicaltest.data.model.contents.TypeService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentLocalRepository @Inject constructor() {
    fun getMedicalFacilities(): List<MedicalFacility> = MedicalFacilityFakeData.data
    fun getProducts(): List<Product> = ProductFakeData.data
    fun getProductServices(): List<ProductService> = ProductServiceFakeData.data
    fun getTypeServices(): List<TypeService> = TypeServiceFakeData.data
}