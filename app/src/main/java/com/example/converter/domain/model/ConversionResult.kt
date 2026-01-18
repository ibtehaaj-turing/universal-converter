package com.example.converter.domain.model

data class ConversionResult(
    val fromValue: Double,
    val fromUnit: ConversionUnit,
    val toValue: Double,
    val toUnit: ConversionUnit
)
