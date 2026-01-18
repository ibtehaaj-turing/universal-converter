package com.example.converter.domain.usecase

import com.example.converter.domain.model.ConversionResult
import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.repository.ConversionRepository
import javax.inject.Inject

class ConvertValueUseCase @Inject constructor(
    private val repository: ConversionRepository
) {
    operator fun invoke(value: Double, fromUnit: ConversionUnit, toUnit: ConversionUnit): ConversionResult {
        val convertedValue = repository.convert(value, fromUnit, toUnit)
        return ConversionResult(
            fromValue = value,
            fromUnit = fromUnit,
            toValue = convertedValue,
            toUnit = toUnit
        )
    }
}
