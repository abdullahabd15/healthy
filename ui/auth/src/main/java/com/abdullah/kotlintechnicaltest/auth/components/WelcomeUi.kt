package com.abdullah.kotlintechnicaltest.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.abdullah.kotlintechnicaltest.auth.R
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.TextSecondary

@Composable
fun WelcomeUi(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.welcome),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            color = TextPrimary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.login_to_continue),
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_login),
            contentDescription = null,
            alignment = Alignment.TopEnd,
            modifier = Modifier
                .fillMaxWidth()
                .height(236.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun WelcomePreview() {
    WelcomeUi()
}