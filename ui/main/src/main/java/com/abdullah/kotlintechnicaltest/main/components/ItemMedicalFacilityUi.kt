package com.abdullah.kotlintechnicaltest.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdullah.kotlintechnicaltest.reservation.R
import com.abdullah.kotlintechnicaltest.shared.theme.DarkBlue
import com.abdullah.kotlintechnicaltest.shared.theme.Orange
import com.abdullah.kotlintechnicaltest.shared.theme.TextGrey
import com.abdullah.kotlintechnicaltest.shared.theme.TextLightGrey
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.White

@Composable
fun ItemMedicalFacilityUi(
    name: String?,
    price: String?,
    locationName: String?,
    locationDetail: String?,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White
        ), shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.65f)
                    .padding(start = 16.dp)
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name ?: "",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = price ?: "", color = Orange)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.ic_hospital_blue),
                        contentDescription = null,
                        tint = DarkBlue
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = locationName ?: "", color = TextGrey)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = null,
                        tint = DarkBlue
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = locationDetail ?: "", color = TextLightGrey)
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.35f, fill = true)
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.img_day_building),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun ItemMedicalFacilityPreview() {
    ItemMedicalFacilityUi(
        name = "PCR Test",
        price = "1.400.000",
        locationName = "Gedung Satria",
        locationDetail = "Jalan Dukuh Surabaya"
    )
}