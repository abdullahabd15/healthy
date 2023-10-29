package com.abdullah.kotlintechnicaltest.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdullah.kotlintechnicaltest.reservation.R
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.TextSecondary
import com.abdullah.kotlintechnicaltest.shared.theme.White

@Composable
fun ServicesCardUi(
    onRegisterTestClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
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
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.special_services),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.covid_test_to_avoid_corona_virus),
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    color = TextSecondary,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.clickable {
                        onRegisterTestClick.invoke()
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.register_test),
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Rounded.ArrowForward,
                        contentDescription = null,
                        tint = TextPrimary
                    )
                }
            }
        }
        Image(
            modifier = Modifier
                .offset(y = (-24).dp)
                .align(Alignment.TopEnd)
                .size(112.dp),
            painter = painterResource(id = R.drawable.img_covid_vaccine),
            contentDescription = null
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun ServicesCardPreview() {
    ServicesCardUi(onRegisterTestClick = {})
}