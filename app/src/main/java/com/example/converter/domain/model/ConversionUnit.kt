package com.example.converter.domain.model

data class ConversionUnit(
    val id: String,
    val name: String,
    val symbol: String,
    val category: ConverterCategory,
    val toBaseFactor: Double
)
