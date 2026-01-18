package com.example.converter.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ConversionUnitTest {

    @Test
    fun `create ConversionUnit with valid parameters`() {
        val unit = ConversionUnit(
            id = "m",
            name = "Meter",
            symbol = "m",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1.0
        )

        assertEquals("m", unit.id)
        assertEquals("Meter", unit.name)
        assertEquals("m", unit.symbol)
        assertEquals(ConverterCategory.LENGTH, unit.category)
        assertEquals(1.0, unit.toBaseFactor, 0.0001)
    }

    @Test
    fun `ConversionUnit equality works correctly`() {
        val unit1 = ConversionUnit(
            id = "m",
            name = "Meter",
            symbol = "m",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1.0
        )

        val unit2 = ConversionUnit(
            id = "m",
            name = "Meter",
            symbol = "m",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1.0
        )

        val unit3 = ConversionUnit(
            id = "km",
            name = "Kilometer",
            symbol = "km",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1000.0
        )

        assertEquals(unit1, unit2)
        assertNotEquals(unit1, unit3)
    }

    @Test
    fun `ConversionUnit copy works correctly`() {
        val original = ConversionUnit(
            id = "m",
            name = "Meter",
            symbol = "m",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1.0
        )

        val copied = original.copy(id = "km", name = "Kilometer", symbol = "km", toBaseFactor = 1000.0)

        assertEquals("km", copied.id)
        assertEquals("Kilometer", copied.name)
        assertEquals("km", copied.symbol)
        assertEquals(ConverterCategory.LENGTH, copied.category)
        assertEquals(1000.0, copied.toBaseFactor, 0.0001)
    }

    @Test
    fun `ConversionUnit hashCode is consistent`() {
        val unit1 = ConversionUnit(
            id = "m",
            name = "Meter",
            symbol = "m",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1.0
        )

        val unit2 = ConversionUnit(
            id = "m",
            name = "Meter",
            symbol = "m",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1.0
        )

        assertEquals(unit1.hashCode(), unit2.hashCode())
    }

    @Test
    fun `ConversionUnit toString contains all fields`() {
        val unit = ConversionUnit(
            id = "m",
            name = "Meter",
            symbol = "m",
            category = ConverterCategory.LENGTH,
            toBaseFactor = 1.0
        )

        val stringRepresentation = unit.toString()

        assert(stringRepresentation.contains("m"))
        assert(stringRepresentation.contains("Meter"))
        assert(stringRepresentation.contains("LENGTH"))
        assert(stringRepresentation.contains("1.0"))
    }
}
