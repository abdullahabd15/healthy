package com.abdullah.kotlintechnicaltest.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdullah.kotlintechnicaltest.shared.R
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.White

@Composable
fun TextInputUi(
    title: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val isPasswordInput = keyboardType == KeyboardType.Password
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = TextPrimary,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(12.dp)
                ),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = placeholder)
            },
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType, imeAction = imeAction,
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedContainerColor = White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            visualTransformation = if (isPasswordInput && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isPasswordInput) {
                    val icon = if (passwordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            modifier = Modifier.fillMaxWidth()
                                .padding(8.dp)
                                .size(24.dp),
                            painter = painterResource(id = icon),
                            contentDescription = null
                        )
                    }
                }
            },
        )
    }
}


@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun TextInputPreview() {
    TextInputUi(title = "Email",
        placeholder = "Masukkan Email Anda",
        value = "john@gmail.com",
        onValueChange = {})
}