package com.example.converter.domain.usecase

import com.example.converter.domain.model.ConverterCategory
import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.repository.ConversionRepository
import javax.inject.Inject

class GetUnitsForCategoryUseCase @Inject constructor(
    private val repository: ConversionRepository
) {
    operator fun invoke(category: ConverterCategory): List<ConversionUnit> {
        return repository.getUnitsForCategory(category)
    }
}
