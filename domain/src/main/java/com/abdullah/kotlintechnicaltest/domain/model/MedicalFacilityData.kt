package com.abdullah.kotlintechnicaltest.domain.model

import com.abdullah.kotlintechnicaltest.data.model.contents.MedicalFacility

data class MedicalFacilityData(
    val id: Int?,
    val name: String?,
    val price: String?,
    val building: String?,
    val location: String?,
)

fun MedicalFacility.mapToData(): MedicalFacilityData =
    MedicalFacilityData(id, name, price, building, location)
