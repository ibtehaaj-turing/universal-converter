package com.example.converter.domain.usecase

import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.domain.repository.ConversionRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetUnitsForCategoryUseCaseTest {

    private lateinit var repository: ConversionRepository
    private lateinit var useCase: GetUnitsForCategoryUseCase

    private val lengthUnits = listOf(
        ConversionUnit("mm", "Millimeter", "mm", ConverterCategory.LENGTH, 0.001),
        ConversionUnit("cm", "Centimeter", "cm", ConverterCategory.LENGTH, 0.01),
        ConversionUnit("m", "Meter", "m", ConverterCategory.LENGTH, 1.0),
        ConversionUnit("km", "Kilometer", "km", ConverterCategory.LENGTH, 1000.0)
    )

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetUnitsForCategoryUseCase(repository)
    }

    @Test
    fun `invoke returns units for LENGTH category`() {
        every { repository.getUnitsForCategory(ConverterCategory.LENGTH) } returns lengthUnits

        val result = useCase(ConverterCategory.LENGTH)

        assertEquals(lengthUnits, result)
        assertEquals(4, result.size)
        verify(exactly = 1) { repository.getUnitsForCategory(ConverterCategory.LENGTH) }
    }

    @Test
    fun `invoke returns empty list when no units available`() {
        every { repository.getUnitsForCategory(ConverterCategory.LENGTH) } returns emptyList()

        val result = useCase(ConverterCategory.LENGTH)

        assertTrue(result.isEmpty())
        verify(exactly = 1) { repository.getUnitsForCategory(ConverterCategory.LENGTH) }
    }

    @Test
    fun `invoke delegates to repository correctly`() {
        every { repository.getUnitsForCategory(any()) } returns lengthUnits

        useCase(ConverterCategory.LENGTH)

        verify { repository.getUnitsForCategory(ConverterCategory.LENGTH) }
    }

    @Test
    fun `invoke returns correct unit properties`() {
        every { repository.getUnitsForCategory(ConverterCategory.LENGTH) } returns lengthUnits

        val result = useCase(ConverterCategory.LENGTH)

        val meter = result.find { it.id == "m" }
        assertEquals("Meter", meter?.name)
        assertEquals("m", meter?.symbol)
        assertEquals(1.0, meter?.toBaseFactor ?: 0.0, 0.0001)
    }
}
