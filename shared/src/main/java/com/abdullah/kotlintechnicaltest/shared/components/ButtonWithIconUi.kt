package com.abdullah.kotlintechnicaltest.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdullah.kotlintechnicaltest.shared.theme.DarkBlue

@Composable
fun ButtonWithIconUi(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedButton(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkBlue
        ),
        onClick = onClick
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Spacer(modifier = Modifier)
            Text(text = text)
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = icon, contentDescription = null
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ButtonWithIconPreview() {
    ButtonWithIconUi(text = "Login", icon = Icons.Rounded.ArrowForward, onClick = {})
}