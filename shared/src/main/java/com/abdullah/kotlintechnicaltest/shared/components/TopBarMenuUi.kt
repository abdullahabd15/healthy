package com.abdullah.kotlintechnicaltest.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdullah.kotlintechnicaltest.shared.R

@Composable
fun TopBarMenuUi(
    onMenuClick: () -> Unit,
    onCartClick: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Image(modifier = Modifier.clickable {
            onMenuClick.invoke()
        }, painter = painterResource(id = R.drawable.ic_dash_menu), contentDescription = null)
        Row(modifier = Modifier) {
            Image(
                modifier = Modifier.clickable {
                    onCartClick.invoke()
                },
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier.clickable {
                onNotificationClick.invoke()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = null
                )
                Image(
                    modifier = modifier.align(Alignment.TopEnd),
                    painter = painterResource(id = R.drawable.ic_new_notification),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun TopBarMenuPreview() {
    TopBarMenuUi(onMenuClick = {}, onCartClick = {}, onNotificationClick = {})
}