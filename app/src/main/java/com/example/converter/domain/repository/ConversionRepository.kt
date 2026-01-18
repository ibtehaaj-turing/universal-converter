package com.example.converter.domain.repository

import com.example.converter.domain.model.ConverterCategory
import com.example.converter.domain.model.ConversionUnit

interface ConversionRepository {
    fun getUnitsForCategory(category: ConverterCategory): List<ConversionUnit>
    fun convert(value: Double, fromUnit: ConversionUnit, toUnit: ConversionUnit): Double
}
