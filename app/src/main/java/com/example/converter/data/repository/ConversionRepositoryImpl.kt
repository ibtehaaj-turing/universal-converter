package com.example.converter.data.repository

import com.example.converter.domain.model.ConverterCategory
import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.repository.ConversionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversionRepositoryImpl @Inject constructor() : ConversionRepository {

    private val lengthUnits = listOf(
        ConversionUnit(
            id = "mm",
            name = "Millimeter",
            symbol = "mm",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 0.001
        ),
        ConversionUnit(
            id = "cm",
            name = "Centimeter",
            symbol = "cm",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 0.01
        ),
        ConversionUnit(
            id = "m",
            name = "Meter",
            symbol = "m",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1.0
        ),
        ConversionUnit(
            id = "km",
            name = "Kilometer",
            symbol = "km",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1000.0
        ),
        ConversionUnit(
            id = "in",
            name = "Inch",
            symbol = "in",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 0.0254
        ),
        ConversionUnit(
            id = "ft",
            name = "Foot",
            symbol = "ft",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 0.3048
        ),
        ConversionUnit(
            id = "yd",
            name = "Yard",
            symbol = "yd",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 0.9144
        ),
        ConversionUnit(
            id = "mi",
            name = "Mile",
            symbol = "mi",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1609.344
        )
    )

    override fun getUnitsForCategory(category: ConverterCategory): List<ConversionUnit> {
        return when (category) {
            ConverterCategory.LENGTH -> lengthUnits
        }
    }

    override fun convert(value: Double, fromUnit: ConversionUnit, toUnit: ConversionUnit): Double {
        val valueInBase = value * fromUnit.toBaseFactor
        return valueInBase / toUnit.toBaseFactor
    }
}
