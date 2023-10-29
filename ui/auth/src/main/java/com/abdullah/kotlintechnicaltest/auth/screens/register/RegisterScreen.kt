package com.abdullah.kotlintechnicaltest.auth.screens.register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
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

@Composable
fun RegisterScreen(
    onLoginClick: () -> Unit,
    onRegisterSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val errorState = viewModel.errorState.collectAsState(initial = "")
    val context = LocalContext.current
    if (viewModel.registerState.value) {
        val msgSuccess = stringResource(R.string.msg_register_succeed_please_login)
        LaunchedEffect(key1 = context, block = {
            Toast.makeText(context, msgSuccess, Toast.LENGTH_SHORT).show()
        })
        onRegisterSuccess.invoke()
    }
    if (errorState.value.isNotBlank()) {
        LaunchedEffect(key1 = context, block = {
            Toast.makeText(context, errorState.value, Toast.LENGTH_SHORT).show()
        })
    }

    RegisterUi(
        modifier = modifier,
        onLogin = onLoginClick,
        onRegister = { firstName, lastName, ktpNumber, email, phoneNumber, password, confirmPassword ->
            viewModel.register(
                firstName = firstName,
                lastName = lastName,
                ktpNumber = ktpNumber,
                email = email,
                phoneNumber = phoneNumber,
                password = password,
                confirmPassword = confirmPassword
            )
        },
    )
}

@Composable
private fun RegisterUi(
    onLogin: () -> Unit,
    onRegister: (String, String, String, String, String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var ktpNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(state = rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            WelcomeUi(modifier = Modifier)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                TextInputUi(
                    modifier = Modifier.weight(0.5f),
                    title = stringResource(R.string.first_name),
                    value = firstName,
                    onValueChange = { firstName = it },
                    placeholder = "",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
                Spacer(modifier = Modifier.width(12.dp))
                TextInputUi(
                    modifier = Modifier.weight(0.5f),
                    title = stringResource(R.string.last_name),
                    value = lastName,
                    onValueChange = { lastName = it },
                    placeholder = "",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextInputUi(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.id_card_number),
                value = ktpNumber,
                onValueChange = { ktpNumber = it },
                placeholder = stringResource(R.string.enter_your_id_card_number),
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextInputUi(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                title = stringResource(id = R.string.email),
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(id = R.string.enter_your_email),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextInputUi(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.phone_number),
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                placeholder = stringResource(R.string.enter_your_phone_number),
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextInputUi(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                title = stringResource(id = R.string.password),
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(id = R.string.enter_your_password),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextInputUi(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.confirm_password),
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = stringResource(id = R.string.enter_your_password),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(24.dp))
            ButtonWithIconUi(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.register),
                icon = Icons.Rounded.ArrowForward,
                onClick = {
                    onRegister.invoke(
                        firstName,
                        lastName,
                        ktpNumber,
                        email,
                        phoneNumber,
                        password,
                        confirmPassword
                    )
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.quest_already_registered), color = TextLightGrey
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.clickable {
                        onLogin.invoke()
                    },
                    text = stringResource(R.string.login_now),
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
@Preview(showBackground = true)
private fun RegisterPreview() {
    RegisterUi(modifier = Modifier, onLogin = {}, onRegister = { _, _, _, _, _, _, _ -> })
}