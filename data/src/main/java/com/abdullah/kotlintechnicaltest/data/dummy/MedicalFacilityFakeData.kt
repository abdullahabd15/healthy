package com.abdullah.kotlintechnicaltest.data.dummy

import com.abdullah.kotlintechnicaltest.data.model.contents.MedicalFacility

object MedicalFacilityFakeData {
    val data = listOf(
        MedicalFacility(
            id = 1,
            name = "PCR Swab Test",
            price = "Rp. 1.400.000",
            building = "Menara Surabaya",
            location = "Jalan Surabaya no 15, Surabaya"
        ),
        MedicalFacility(
            id = 2,
            name = "Genose Swab Test",
            price = "Rp. 10.000",
            building = "Stasiun Surabaya",
            location = "Jalan Surabaya no 25, Surabaya"
        ),
        MedicalFacility(
            id = 3,
            name = "Rapid Test",
            price = "Rp. 275.000",
            building = "Menara Sudirman",
            location = "Jalan Sudirman no 15, Jakarta"
        )
    )
}