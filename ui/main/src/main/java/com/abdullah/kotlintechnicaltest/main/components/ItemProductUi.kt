package com.abdullah.kotlintechnicaltest.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdullah.kotlintechnicaltest.reservation.R
import com.abdullah.kotlintechnicaltest.shared.theme.DarkGreen
import com.abdullah.kotlintechnicaltest.shared.theme.LightGreen
import com.abdullah.kotlintechnicaltest.shared.theme.Orange
import com.abdullah.kotlintechnicaltest.shared.theme.TextLightGrey
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.White
import com.abdullah.kotlintechnicaltest.shared.theme.Yellow

@Composable
fun ItemProductUi(
    productName: String?,
    price: String?,
    status: String?,
    rating: Float?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(124.dp),
                    painter = painterResource(id = R.drawable.img_microscope),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = productName ?: "",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = price ?: "",
                        color = Orange,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                    Text(
                        text = status ?: "",
                        modifier = Modifier
                            .background(color = LightGreen, shape = RoundedCornerShape(4.dp))
                            .padding(vertical = 2.dp, horizontal = 4.dp),
                        color = DarkGreen,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
        ) {
            Icon(imageVector = Icons.Rounded.Star, contentDescription = null, tint = Yellow)
            Text(text = rating.toString(), color = TextLightGrey)
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun ItemProductPreview() {
    ItemProductUi(
        productName = "Suntik Steril",
        price = "10.000",
        status = "Ready Stock",
        rating = 5f
    )
}