package com.abdullah.kotlintechnicaltest.main.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdullah.kotlintechnicaltest.domain.model.MedicalFacilityData
import com.abdullah.kotlintechnicaltest.domain.model.ProductData
import com.abdullah.kotlintechnicaltest.domain.model.ProductServiceData
import com.abdullah.kotlintechnicaltest.domain.model.TypeServiceData
import com.abdullah.kotlintechnicaltest.main.components.ItemMedicalFacilityUi
import com.abdullah.kotlintechnicaltest.main.components.ItemProductUi
import com.abdullah.kotlintechnicaltest.main.components.SearchBarUi
import com.abdullah.kotlintechnicaltest.main.components.ServicesCardUi
import com.abdullah.kotlintechnicaltest.main.components.TopBannerUi
import com.abdullah.kotlintechnicaltest.main.components.TrackHistoryCardUi
import com.abdullah.kotlintechnicaltest.reservation.R
import com.abdullah.kotlintechnicaltest.shared.components.SnackBarNotificationSuggestionUi
import com.abdullah.kotlintechnicaltest.shared.components.TabRowUi
import com.abdullah.kotlintechnicaltest.shared.components.TopBarMenuUi
import com.abdullah.kotlintechnicaltest.shared.theme.DarkBlue
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.White

@Composable
fun MainScreen(
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val medicalFacilities = viewModel.medicalFacilitiesState.collectAsState()
    val products = viewModel.productsState.collectAsState()
    val productServices = viewModel.productServicesState.collectAsState()
    val typeServices = viewModel.typeServicesState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.apply {
            getMedicalFacilities()
            getProducts()
            getProductServices()
            getTypeServices()
        }
    })

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        MainUi(
            modifier = Modifier,
            onMenuClick = onMenuClick,
            medicalFacilities = medicalFacilities.value,
            products = products.value,
            productServices = productServices.value,
            typeServices = typeServices.value
        )
        SnackBarNotificationSuggestionUi(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MainUi(
    medicalFacilities: List<MedicalFacilityData>,
    products: List<ProductData>,
    productServices: List<ProductServiceData>,
    typeServices: List<TypeServiceData>,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        TopBarMenuUi(
            modifier = Modifier.padding(horizontal = 16.dp),
            onMenuClick = onMenuClick,
            onCartClick = {},
            onNotificationClick = {}
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TopBannerUi(
                modifier = Modifier.padding(horizontal = 16.dp),
                onViewMore = {

                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ServicesCardUi(
                modifier = Modifier.padding(horizontal = 16.dp),
                onRegisterTestClick = {

                })
            Spacer(modifier = Modifier.height(16.dp))
            TrackHistoryCardUi(
                modifier = Modifier.padding(horizontal = 16.dp),
                onTrackClick = {

                })
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {
                    Image(
                        modifier = Modifier.padding(12.dp),
                        painter = painterResource(id = R.drawable.ic_filter_blue),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                SearchBarUi(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TabRowUi(
                titles = productServices.map { it.name ?: "" },
                modifier = Modifier,
                selectedTitleColor = White,
                unselectedTitleColor = TextPrimary,
                selectedBackgroundColor = DarkBlue,
                unselectedBackgroundColor = White,
                scrollable = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            FlowColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(products.size / 2) {
                    val itemModifier = Modifier
                        .padding(1.dp)
                        .fillMaxWidth()
                        .weight(1f)
                    Box(modifier = itemModifier) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            products.forEach {
                                ItemProductUi(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1f),
                                    productName = it.name,
                                    price = it.price,
                                    status = it.status,
                                    rating = it.rating
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.choose_medical_service_type)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TabRowUi(titles = typeServices.map { it.name ?: "" }, scrollable = true)
            Spacer(modifier = Modifier.height(16.dp))
            FlowColumn(
                modifier = Modifier
                    .size(LocalConfiguration.current.screenWidthDp.dp)
                    .padding(horizontal = 16.dp),
            ) {
                medicalFacilities.forEach {
                    ItemMedicalFacilityUi(
                        name = it.name,
                        price = it.price,
                        locationName = it.building,
                        locationDetail = it.location
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(72.dp))
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MainPreview() {
    MainUi(
        medicalFacilities = listOf(),
        products = listOf(),
        productServices = listOf(),
        typeServices = listOf(),
        onMenuClick = { })
}