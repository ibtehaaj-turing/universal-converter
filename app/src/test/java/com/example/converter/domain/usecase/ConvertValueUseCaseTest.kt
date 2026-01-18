package com.example.converter.domain.usecase

import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.domain.repository.ConversionRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConvertValueUseCaseTest {

    private lateinit var repository: ConversionRepository
    private lateinit var useCase: ConvertValueUseCase

    private val meterUnit = ConversionUnit(
        id = "m",
        name = "Meter",
        symbol = "m",
        category = ConverterCategory.LENGTH,
        toBaseFactor = 1.0
    )

    private val kilometerUnit = ConversionUnit(
        id = "km",
        name = "Kilometer",
        symbol = "km",
        category = ConverterCategory.LENGTH,
        toBaseFactor = 1000.0
    )

    private val centimeterUnit = ConversionUnit(
        id = "cm",
        name = "Centimeter",
        symbol = "cm",
        category = ConverterCategory.LENGTH,
        toBaseFactor = 0.01
    )

    @Before
    fun setUp() {
        repository = mockk()
        useCase = ConvertValueUseCase(repository)
    }

    @Test
    fun `invoke converts meters to kilometers correctly`() {
        every { repository.convert(1000.0, meterUnit, kilometerUnit) } returns 1.0

        val result = useCase(1000.0, meterUnit, kilometerUnit)

        assertEquals(1000.0, result.fromValue, 0.0001)
        assertEquals(meterUnit, result.fromUnit)
        assertEquals(1.0, result.toValue, 0.0001)
        assertEquals(kilometerUnit, result.toUnit)
        verify(exactly = 1) { repository.convert(1000.0, meterUnit, kilometerUnit) }
    }

    @Test
    fun `invoke converts kilometers to meters correctly`() {
        every { repository.convert(1.0, kilometerUnit, meterUnit) } returns 1000.0

        val result = useCase(1.0, kilometerUnit, meterUnit)

        assertEquals(1.0, result.fromValue, 0.0001)
        assertEquals(kilometerUnit, result.fromUnit)
        assertEquals(1000.0, result.toValue, 0.0001)
        assertEquals(meterUnit, result.toUnit)
    }

    @Test
    fun `invoke handles zero value`() {
        every { repository.convert(0.0, meterUnit, kilometerUnit) } returns 0.0

        val result = useCase(0.0, meterUnit, kilometerUnit)

        assertEquals(0.0, result.fromValue, 0.0001)
        assertEquals(0.0, result.toValue, 0.0001)
    }

    @Test
    fun `invoke handles negative value`() {
        every { repository.convert(-100.0, meterUnit, kilometerUnit) } returns -0.1

        val result = useCase(-100.0, meterUnit, kilometerUnit)

        assertEquals(-100.0, result.fromValue, 0.0001)
        assertEquals(-0.1, result.toValue, 0.0001)
    }

    @Test
    fun `invoke handles same unit conversion`() {
        every { repository.convert(100.0, meterUnit, meterUnit) } returns 100.0

        val result = useCase(100.0, meterUnit, meterUnit)

        assertEquals(100.0, result.fromValue, 0.0001)
        assertEquals(100.0, result.toValue, 0.0001)
        assertEquals(result.fromUnit, result.toUnit)
    }

    @Test
    fun `invoke handles small values`() {
        every { repository.convert(0.001, meterUnit, centimeterUnit) } returns 0.1

        val result = useCase(0.001, meterUnit, centimeterUnit)

        assertEquals(0.001, result.fromValue, 0.0001)
        assertEquals(0.1, result.toValue, 0.0001)
    }

    @Test
    fun `invoke handles large values`() {
        every { repository.convert(1000000.0, meterUnit, kilometerUnit) } returns 1000.0

        val result = useCase(1000000.0, meterUnit, kilometerUnit)

        assertEquals(1000000.0, result.fromValue, 0.0001)
        assertEquals(1000.0, result.toValue, 0.0001)
    }

    @Test
    fun `invoke delegates to repository with correct parameters`() {
        every { repository.convert(any(), any(), any()) } returns 1.0

        useCase(500.0, meterUnit, kilometerUnit)

        verify { repository.convert(500.0, meterUnit, kilometerUnit) }
    }
}
