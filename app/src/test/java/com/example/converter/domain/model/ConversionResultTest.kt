package com.example.converter.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ConversionResultTest {

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

    @Test
    fun `create ConversionResult with valid parameters`() {
        val result = ConversionResult(
            fromValue = 1000.0,
            fromUnit = meterUnit,
            toValue = 1.0,
            toUnit = kilometerUnit
        )

        assertEquals(1000.0, result.fromValue, 0.0001)
        assertEquals(meterUnit, result.fromUnit)
        assertEquals(1.0, result.toValue, 0.0001)
        assertEquals(kilometerUnit, result.toUnit)
    }

    @Test
    fun `ConversionResult equality works correctly`() {
        val result1 = ConversionResult(
            fromValue = 1000.0,
            fromUnit = meterUnit,
            toValue = 1.0,
            toUnit = kilometerUnit
        )

        val result2 = ConversionResult(
            fromValue = 1000.0,
            fromUnit = meterUnit,
            toValue = 1.0,
            toUnit = kilometerUnit
        )

        val result3 = ConversionResult(
            fromValue = 500.0,
            fromUnit = meterUnit,
            toValue = 0.5,
            toUnit = kilometerUnit
        )

        assertEquals(result1, result2)
        assertNotEquals(result1, result3)
    }

    @Test
    fun `ConversionResult copy works correctly`() {
        val original = ConversionResult(
            fromValue = 1000.0,
            fromUnit = meterUnit,
            toValue = 1.0,
            toUnit = kilometerUnit
        )

        val copied = original.copy(fromValue = 2000.0, toValue = 2.0)

        assertEquals(2000.0, copied.fromValue, 0.0001)
        assertEquals(meterUnit, copied.fromUnit)
        assertEquals(2.0, copied.toValue, 0.0001)
        assertEquals(kilometerUnit, copied.toUnit)
    }

    @Test
    fun `ConversionResult hashCode is consistent`() {
        val result1 = ConversionResult(
            fromValue = 1000.0,
            fromUnit = meterUnit,
            toValue = 1.0,
            toUnit = kilometerUnit
        )

        val result2 = ConversionResult(
            fromValue = 1000.0,
            fromUnit = meterUnit,
            toValue = 1.0,
            toUnit = kilometerUnit
        )

        assertEquals(result1.hashCode(), result2.hashCode())
    }

    @Test
    fun `ConversionResult toString contains all fields`() {
        val result = ConversionResult(
            fromValue = 1000.0,
            fromUnit = meterUnit,
            toValue = 1.0,
            toUnit = kilometerUnit
        )

        val stringRepresentation = result.toString()

        assert(stringRepresentation.contains("1000.0"))
        assert(stringRepresentation.contains("1.0"))
    }

    @Test
    fun `ConversionResult with zero values`() {
        val result = ConversionResult(
            fromValue = 0.0,
            fromUnit = meterUnit,
            toValue = 0.0,
            toUnit = kilometerUnit
        )

        assertEquals(0.0, result.fromValue, 0.0001)
        assertEquals(0.0, result.toValue, 0.0001)
    }

    @Test
    fun `ConversionResult with negative values`() {
        val result = ConversionResult(
            fromValue = -100.0,
            fromUnit = meterUnit,
            toValue = -0.1,
            toUnit = kilometerUnit
        )

        assertEquals(-100.0, result.fromValue, 0.0001)
        assertEquals(-0.1, result.toValue, 0.0001)
    }
}
