package com.example.converter.presentation.converter

import com.example.converter.domain.model.ConverterCategory
import com.example.converter.domain.model.ConversionUnit

data class ConverterUiState(
    val category: ConverterCategory = ConverterCategory.LENGTH,
    val inputValue: String = "",
    val fromUnit: ConversionUnit? = null,
    val toUnit: ConversionUnit? = null,
    val result: String = "",
    val availableUnits: List<ConversionUnit> = emptyList()
)
