package com.abdullah.kotlintechnicaltest.main.screens.sidemenu

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdullah.kotlintechnicaltest.reservation.R
import com.abdullah.kotlintechnicaltest.shared.theme.Red
import com.abdullah.kotlintechnicaltest.shared.theme.TextLightGrey
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.TextSecondary

@Composable
fun SideMenuScreen(
    onCloseClick: () -> Unit,
    onLogoutSuccess: () -> Unit,
    onMyProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SideMenuViewModel = hiltViewModel(),
) {
    val errorState = viewModel.errorState.collectAsState(initial = "")
    val context = LocalContext.current
    if (viewModel.logoutState.value) {
        onLogoutSuccess.invoke()
        return
    }

    if (errorState.value.isNotBlank()) {
        LaunchedEffect(key1 = viewModel.errorState, block = {
            Toast.makeText(context, errorState.value, Toast.LENGTH_SHORT).show()
        })
    }

    LaunchedEffect(key1 = viewModel.userState, block = {
        viewModel.getUser()
    })

    SideMenuUi(
        modifier = modifier,
        firstName = viewModel.userState.value?.firstName,
        lastName = viewModel.userState.value?.lastName,
        onCloseClick = onCloseClick,
        onLogoutClick = {
            viewModel.logout()
        },
        onMyProfileClick = onMyProfileClick,
    )
}

@Composable
private fun SideMenuUi(
    firstName: String?,
    lastName: String?,
    onCloseClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onMyProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxHeight()
                .background(color = Color(0xFF002060))
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 36.dp, start = 24.dp, end = 8.dp)
                    .clickable {
                        onCloseClick.invoke()
                    },
                painter = painterResource(id = R.drawable.ic_close_white),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 56.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row {
                    Image(
                        modifier = Modifier.size(64.dp),
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            text = "${firstName ?: ""} ${lastName ?: ""}",
                            color = TextPrimary,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.membership_bblk), color = TextSecondary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onMyProfileClick.invoke()
                    }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        text = stringResource(R.string.my_profile),
                        color = TextLightGrey
                    )
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = TextLightGrey
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        text = stringResource(R.string.settings),
                        color = TextLightGrey
                    )
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = TextLightGrey
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
                Button(
                    modifier = Modifier,
                    onClick = onLogoutClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 42.dp),
                        text = stringResource(R.string.logout)
                    )
                }
                Spacer(modifier = Modifier.height(42.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.follow_me_on), color = TextPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_instagram),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_twitter),
                        contentDescription = null
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.faq), color = TextLightGrey)
                Text(text = stringResource(R.string.terms_and_conditions), color = TextLightGrey)
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun SideMenuPreview() {
    SideMenuUi(firstName = "John",
        lastName = "Doe",
        onCloseClick = {},
        onLogoutClick = {},
        onMyProfileClick = {},
        modifier = Modifier
    )
}

