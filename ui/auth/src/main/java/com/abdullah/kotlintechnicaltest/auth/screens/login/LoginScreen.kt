package com.abdullah.kotlintechnicaltest.auth.screens.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdullah.kotlintechnicaltest.auth.R
import com.abdullah.kotlintechnicaltest.auth.components.WelcomeUi
import com.abdullah.kotlintechnicaltest.shared.components.ButtonWithIconUi
import com.abdullah.kotlintechnicaltest.shared.components.TextInputUi
import com.abdullah.kotlintechnicaltest.shared.theme.TextLightGrey
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.TextSecondary

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val isLoggedIn = viewModel.loginState.value
    val errorMessage = viewModel.errorState.collectAsState(initial = "")
    val context = LocalContext.current

    if (isLoggedIn) {
        onLoginSuccess.invoke()
        return
    }
    if (errorMessage.value.isNotBlank()) {
        LaunchedEffect(key1 = context, block = {
            Toast.makeText(context, errorMessage.value, Toast.LENGTH_SHORT).show()
        })
    }
    LoginUi(modifier = modifier, onLogin = { email, password ->
        viewModel.login(email, password)
    }, onRegister = onRegisterClick)
}

@Composable
private fun LoginUi(
    onLogin: (String, String) -> Unit,
    onRegister: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(36.dp))
            WelcomeUi(modifier = Modifier)
            Spacer(modifier = Modifier.height(12.dp))
            TextInputUi(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = stringResource(R.string.email),
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.enter_your_email),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                TextInputUi(
                    title = stringResource(R.string.password),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = stringResource(R.string.enter_your_password),
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Go,
                )
                Text(
                    text = stringResource(R.string.quest_forgot_your_password),
                    textAlign = TextAlign.End,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = TextSecondary,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            ButtonWithIconUi(
                text = stringResource(R.string.login),
                icon = Icons.Rounded.ArrowForward,
                onClick = {
                    onLogin.invoke(email, password)
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.quest_not_have_account), color = TextLightGrey
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.clickable {
                        onRegister.invoke()
                    },
                    text = stringResource(R.string.register_now),
                    color = TextPrimary,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.silk_all_right_reserved),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = TextLightGrey
            )
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun LoginPreview() {
    LoginUi(modifier = Modifier, onLogin = { email, password -> }, onRegister = {})
}