package com.abdullah.kotlintechnicaltest.profile.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdullah.kotlintechnicaltest.profile.R
import com.abdullah.kotlintechnicaltest.shared.components.ButtonWithIconUi
import com.abdullah.kotlintechnicaltest.shared.components.SnackBarNotificationSuggestionUi
import com.abdullah.kotlintechnicaltest.shared.components.TabRowUi
import com.abdullah.kotlintechnicaltest.shared.components.TextInputUi
import com.abdullah.kotlintechnicaltest.shared.components.TopBarMenuUi
import com.abdullah.kotlintechnicaltest.shared.theme.AquaBlue
import com.abdullah.kotlintechnicaltest.shared.theme.DarkBlue
import com.abdullah.kotlintechnicaltest.shared.theme.TextLightGrey
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.TextSecondary
import com.abdullah.kotlintechnicaltest.shared.theme.White

@Composable
fun ProfileScreen(
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val userState = viewModel.userState.collectAsState(null)
    val errorState = viewModel.errorState.collectAsState(initial = "")
    val updateUserState = viewModel.updateUserState.collectAsState(initial = false)
    val msgUpdateSuccess = stringResource(R.string.msg_update_profile_success)
    val context = LocalContext.current
    if (updateUserState.value) {
        LaunchedEffect(key1 = context, block = {
            Toast.makeText(context, msgUpdateSuccess, Toast.LENGTH_SHORT).show()
        })
    }
    if (errorState.value.isNotBlank()) {
        LaunchedEffect(key1 = context, block = {
            Toast.makeText(context, errorState.value, Toast.LENGTH_SHORT).show()
        })
    }

    LaunchedEffect(key1 = userState, block = {
        viewModel.getProfile()
    })

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ProfileUi(
            modifier = Modifier,
            firstName = userState.value?.firstName,
            lastName = userState.value?.lastName,
            email = userState.value?.email,
            phoneNumber = userState.value?.phoneNumber,
            ktpNumber = userState.value?.ktpNumber,
            onSaveClick = { firstName, lastName, email, phoneNumber, ktpNumber ->
                viewModel.updateProfile(
                    id = userState.value?.id,
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    phoneNumber = phoneNumber,
                    ktpNumber = ktpNumber
                )
            },
            onMenuClick = onMenuClick
        )
        SnackBarNotificationSuggestionUi(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun ProfileUi(
    firstName: String?,
    lastName: String?,
    email: String?,
    phoneNumber: String?,
    ktpNumber: String?,
    onSaveClick: (String, String, String, String, String) -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val profileTabs = listOf(
        stringResource(R.string.my_profile),
        stringResource(R.string.settings)
    )
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            TopBarMenuUi(
                onMenuClick = onMenuClick,
                onCartClick = {},
                onNotificationClick = { },
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(36.dp))
                TabRowUi(
                    titles = profileTabs,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(36.dp))
                ProfileDetailUi(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    phoneNumber = phoneNumber,
                    ktpNumber = ktpNumber,
                    onSaveClick = onSaveClick
                )
                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    }
}

@Composable
private fun ProfileDetailUi(
    firstName: String?,
    lastName: String?,
    email: String?,
    phoneNumber: String?,
    ktpNumber: String?,
    onSaveClick: (String, String, String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var firstNameState by remember { mutableStateOf("") }
    var lastNameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var phoneNumberState by remember { mutableStateOf("") }
    var ktpNumberState by remember { mutableStateOf("") }
    firstNameState = firstName ?: ""
    lastNameState = lastName ?: ""
    emailState = email ?: ""
    phoneNumberState = phoneNumber ?: ""
    ktpNumberState = ktpNumber ?: ""
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Card(
            modifier = Modifier, colors = CardDefaults.cardColors(
                containerColor = DarkBlue
            )
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_circle_corner_right),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier.padding(top = 16.dp),
                        painter = painterResource(id = R.drawable.img_half_ring),
                        contentDescription = null
                    )
                }
                Column {
                    Spacer(modifier = Modifier.height(36.dp))
                    Row {
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(id = R.drawable.img_profile),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                modifier = Modifier.fillMaxWidth(0.8f),
                                text = "$firstName $lastName",
                                color = White,
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(R.string.membership_bblk),
                                color = TextSecondary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF1A3E78),
                                shape = RoundedCornerShape(topStart = 12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        text = stringResource(R.string.msg_complete_your_profile),
                        color = White
                    )
                }
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 36.dp, end = 36.dp),
                    painter = painterResource(id = com.abdullah.kotlintechnicaltest.shared.R.drawable.img_multidot_big),
                    contentDescription = null
                )
            }
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.choose_data_to_display),
                color = TextPrimary,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .background(color = AquaBlue, shape = CircleShape)
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.ic_profile_blue),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = stringResource(R.string.self_data), fontWeight = FontWeight.Bold)
                    Text(
                        text = stringResource(R.string.data_according_to_ktp),
                        fontWeight = FontWeight.Light,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    modifier = Modifier
                        .background(color = TextLightGrey, shape = CircleShape)
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.ic_map_marker_grey),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextInputUi(
                title = stringResource(R.string.first_name),
                placeholder = "",
                value = firstNameState,
                onValueChange = {
                    firstNameState = it
                },
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextInputUi(
                title = stringResource(R.string.last_name),
                placeholder = "",
                value = lastNameState,
                onValueChange = {
                    lastNameState = it
                },
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextInputUi(
                title = stringResource(R.string.email),
                placeholder = stringResource(R.string.enter_your_email),
                value = emailState,
                onValueChange = {
                    emailState = it
                },
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextInputUi(
                title = stringResource(R.string.phone_number),
                placeholder = stringResource(R.string.enter_your_phone_number),
                value = phoneNumberState,
                onValueChange = {
                    phoneNumberState = it
                },
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextInputUi(
                title = stringResource(R.string.ktp_number),
                placeholder = stringResource(R.string.enter_your_ktp_number),
                value = ktpNumberState,
                onValueChange = {
                    ktpNumberState = it
                },
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Image(
                    modifier = Modifier.size(22.dp),
                    painter = painterResource(id = R.drawable.ic_info_circle_blue),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.msg_make_sure_data))
            }
            Spacer(modifier = Modifier.height(24.dp))
            ButtonWithIconUi(
                text = stringResource(R.string.save_profile),
                icon = Icons.Outlined.Save,
                onClick = {
                    onSaveClick.invoke(
                        firstNameState,
                        lastNameState,
                        emailState,
                        phoneNumberState,
                        ktpNumberState
                    )
                },
            )
            Spacer(modifier = Modifier.height(72.dp))
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun ProfilePreview() {
    ProfileUi(
        firstName = "John",
        lastName = "Doe",
        email = "johndoe@gmail.com",
        phoneNumber = "+6287873987878",
        ktpNumber = "14235245346456464",
        onSaveClick = { _, _, _, _, _ ->

        },
        onMenuClick = {},
        modifier = Modifier
    )
}