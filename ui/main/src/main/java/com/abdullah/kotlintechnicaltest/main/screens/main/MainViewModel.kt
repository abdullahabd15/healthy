package com.abdullah.kotlintechnicaltest.main.screens.main

import androidx.lifecycle.viewModelScope
import com.abdullah.kotlintechnicaltest.domain.model.MedicalFacilityData
import com.abdullah.kotlintechnicaltest.domain.model.ProductData
import com.abdullah.kotlintechnicaltest.domain.model.ProductServiceData
import com.abdullah.kotlintechnicaltest.domain.model.TypeServiceData
import com.abdullah.kotlintechnicaltest.domain.usecases.contents.GetMedicalFacilitiesUseCase
import com.abdullah.kotlintechnicaltest.domain.usecases.contents.GetProductServicesUseCase
import com.abdullah.kotlintechnicaltest.domain.usecases.contents.GetProductsUseCase
import com.abdullah.kotlintechnicaltest.domain.usecases.contents.GetTypeServicesUseCase
import com.abdullah.kotlintechnicaltest.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMedicalFacilitiesUseCase: GetMedicalFacilitiesUseCase,
    private val getProductServicesUseCase: GetProductServicesUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val getTypeServicesUseCase: GetTypeServicesUseCase
) : BaseViewModel() {

    private val _medicalFacilitiesState = MutableStateFlow<List<MedicalFacilityData>>(listOf())
    val medicalFacilitiesState get() = _medicalFacilitiesState.asStateFlow()

    private val _productServicesState = MutableStateFlow<List<ProductServiceData>>(listOf())
    val productServicesState get() = _productServicesState.asStateFlow()

    private val _productsState = MutableStateFlow<List<ProductData>>(listOf())
    val productsState get() = _productsState.asStateFlow()

    private val _typeServicesState = MutableStateFlow<List<TypeServiceData>>(listOf())
    val typeServicesState get() = _typeServicesState.asStateFlow()

    fun getMedicalFacilities() {
        viewModelScope.launch {
            _medicalFacilitiesState.value = getMedicalFacilitiesUseCase()
        }
    }

    fun getProductServices() {
        viewModelScope.launch {
            _productServicesState.value = getProductServicesUseCase()
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            _productsState.value = getProductsUseCase()
        }
    }

    fun getTypeServices() {
        viewModelScope.launch {
            _typeServicesState.value = getTypeServicesUseCase()
        }
    }
}