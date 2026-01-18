package com.example.converter.data.repository

import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.model.ConverterCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ConversionRepositoryImplTest {

    private lateinit var repository: ConversionRepositoryImpl

    @Before
    fun setUp() {
        repository = ConversionRepositoryImpl()
    }

    // getUnitsForCategory tests

    @Test
    fun `getUnitsForCategory returns 8 length units`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        assertEquals(8, units.size)
    }

    @Test
    fun `getUnitsForCategory returns all expected length units`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val unitIds = units.map { it.id }

        assertTrue(unitIds.contains("mm"))
        assertTrue(unitIds.contains("cm"))
        assertTrue(unitIds.contains("m"))
        assertTrue(unitIds.contains("km"))
        assertTrue(unitIds.contains("in"))
        assertTrue(unitIds.contains("ft"))
        assertTrue(unitIds.contains("yd"))
        assertTrue(unitIds.contains("mi"))
    }

    @Test
    fun `getUnitsForCategory returns units with correct category`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        units.forEach { unit ->
            assertEquals(ConverterCategory.LENGTH, unit.category)
        }
    }

    @Test
    fun `getUnitsForCategory returns millimeter with correct properties`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val mm = units.find { it.id == "mm" }

        assertNotNull(mm)
        assertEquals("Millimeter", mm?.name)
        assertEquals("mm", mm?.symbol)
        assertEquals(0.001, mm?.toBaseFactor ?: 0.0, 0.0001)
    }

    @Test
    fun `getUnitsForCategory returns centimeter with correct properties`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val cm = units.find { it.id == "cm" }

        assertNotNull(cm)
        assertEquals("Centimeter", cm?.name)
        assertEquals("cm", cm?.symbol)
        assertEquals(0.01, cm?.toBaseFactor ?: 0.0, 0.0001)
    }

    @Test
    fun `getUnitsForCategory returns meter with correct properties`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val m = units.find { it.id == "m" }

        assertNotNull(m)
        assertEquals("Meter", m?.name)
        assertEquals("m", m?.symbol)
        assertEquals(1.0, m?.toBaseFactor ?: 0.0, 0.0001)
    }

    @Test
    fun `getUnitsForCategory returns kilometer with correct properties`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val km = units.find { it.id == "km" }

        assertNotNull(km)
        assertEquals("Kilometer", km?.name)
        assertEquals("km", km?.symbol)
        assertEquals(1000.0, km?.toBaseFactor ?: 0.0, 0.0001)
    }

    @Test
    fun `getUnitsForCategory returns inch with correct properties`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val inch = units.find { it.id == "in" }

        assertNotNull(inch)
        assertEquals("Inch", inch?.name)
        assertEquals("in", inch?.symbol)
        assertEquals(0.0254, inch?.toBaseFactor ?: 0.0, 0.0001)
    }

    @Test
    fun `getUnitsForCategory returns foot with correct properties`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val ft = units.find { it.id == "ft" }

        assertNotNull(ft)
        assertEquals("Foot", ft?.name)
        assertEquals("ft", ft?.symbol)
        assertEquals(0.3048, ft?.toBaseFactor ?: 0.0, 0.0001)
    }

    @Test
    fun `getUnitsForCategory returns yard with correct properties`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val yd = units.find { it.id == "yd" }

        assertNotNull(yd)
        assertEquals("Yard", yd?.name)
        assertEquals("yd", yd?.symbol)
        assertEquals(0.9144, yd?.toBaseFactor ?: 0.0, 0.0001)
    }

    @Test
    fun `getUnitsForCategory returns mile with correct properties`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val mi = units.find { it.id == "mi" }

        assertNotNull(mi)
        assertEquals("Mile", mi?.name)
        assertEquals("mi", mi?.symbol)
        assertEquals(1609.344, mi?.toBaseFactor ?: 0.0, 0.001)
    }

    // convert tests - Metric conversions

    @Test
    fun `convert meters to kilometers`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val m = units.find { it.id == "m" }!!
        val km = units.find { it.id == "km" }!!

        val result = repository.convert(1000.0, m, km)
        assertEquals(1.0, result, 0.0001)
    }

    @Test
    fun `convert kilometers to meters`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val m = units.find { it.id == "m" }!!
        val km = units.find { it.id == "km" }!!

        val result = repository.convert(1.0, km, m)
        assertEquals(1000.0, result, 0.0001)
    }

    @Test
    fun `convert centimeters to meters`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val cm = units.find { it.id == "cm" }!!
        val m = units.find { it.id == "m" }!!

        val result = repository.convert(100.0, cm, m)
        assertEquals(1.0, result, 0.0001)
    }

    @Test
    fun `convert millimeters to centimeters`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val mm = units.find { it.id == "mm" }!!
        val cm = units.find { it.id == "cm" }!!

        val result = repository.convert(10.0, mm, cm)
        assertEquals(1.0, result, 0.0001)
    }

    // convert tests - Imperial conversions

    @Test
    fun `convert feet to inches`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val ft = units.find { it.id == "ft" }!!
        val inch = units.find { it.id == "in" }!!

        val result = repository.convert(1.0, ft, inch)
        assertEquals(12.0, result, 0.0001)
    }

    @Test
    fun `convert yards to feet`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val yd = units.find { it.id == "yd" }!!
        val ft = units.find { it.id == "ft" }!!

        val result = repository.convert(1.0, yd, ft)
        assertEquals(3.0, result, 0.0001)
    }

    @Test
    fun `convert miles to yards`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val mi = units.find { it.id == "mi" }!!
        val yd = units.find { it.id == "yd" }!!

        val result = repository.convert(1.0, mi, yd)
        assertEquals(1760.0, result, 0.001)
    }

    // convert tests - Cross-system conversions

    @Test
    fun `convert inches to centimeters`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val inch = units.find { it.id == "in" }!!
        val cm = units.find { it.id == "cm" }!!

        val result = repository.convert(1.0, inch, cm)
        assertEquals(2.54, result, 0.0001)
    }

    @Test
    fun `convert feet to meters`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val ft = units.find { it.id == "ft" }!!
        val m = units.find { it.id == "m" }!!

        val result = repository.convert(1.0, ft, m)
        assertEquals(0.3048, result, 0.0001)
    }

    @Test
    fun `convert miles to kilometers`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val mi = units.find { it.id == "mi" }!!
        val km = units.find { it.id == "km" }!!

        val result = repository.convert(1.0, mi, km)
        assertEquals(1.609344, result, 0.0001)
    }

    // convert tests - Edge cases

    @Test
    fun `convert same unit returns same value`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val m = units.find { it.id == "m" }!!

        val result = repository.convert(100.0, m, m)
        assertEquals(100.0, result, 0.0001)
    }

    @Test
    fun `convert zero value returns zero`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val m = units.find { it.id == "m" }!!
        val km = units.find { it.id == "km" }!!

        val result = repository.convert(0.0, m, km)
        assertEquals(0.0, result, 0.0001)
    }

    @Test
    fun `convert negative value returns negative result`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val m = units.find { it.id == "m" }!!
        val km = units.find { it.id == "km" }!!

        val result = repository.convert(-1000.0, m, km)
        assertEquals(-1.0, result, 0.0001)
    }

    @Test
    fun `convert very small value`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val mm = units.find { it.id == "mm" }!!
        val km = units.find { it.id == "km" }!!

        val result = repository.convert(1.0, mm, km)
        assertEquals(0.000001, result, 0.0000001)
    }

    @Test
    fun `convert very large value`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val km = units.find { it.id == "km" }!!
        val mm = units.find { it.id == "mm" }!!

        val result = repository.convert(1.0, km, mm)
        assertEquals(1000000.0, result, 0.0001)
    }

    @Test
    fun `convert fractional value`() {
        val units = repository.getUnitsForCategory(ConverterCategory.LENGTH)
        val m = units.find { it.id == "m" }!!
        val cm = units.find { it.id == "cm" }!!

        val result = repository.convert(0.5, m, cm)
        assertEquals(50.0, result, 0.0001)
    }
}
