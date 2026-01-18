package com.example.converter.presentation.converter

import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.model.ConverterCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ConverterUiStateTest {

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
    fun `default ConverterUiState has correct initial values`() {
        val state = ConverterUiState()

        assertEquals(ConverterCategory.LENGTH, state.category)
        assertEquals("", state.inputValue)
        assertNull(state.fromUnit)
        assertNull(state.toUnit)
        assertEquals("", state.result)
        assertTrue(state.availableUnits.isEmpty())
    }

    @Test
    fun `ConverterUiState can be created with custom values`() {
        val units = listOf(meterUnit, kilometerUnit)
        val state = ConverterUiState(
            category = ConverterCategory.LENGTH,
            inputValue = "100",
            fromUnit = meterUnit,
            toUnit = kilometerUnit,
            result = "0.1",
            availableUnits = units
        )

        assertEquals(ConverterCategory.LENGTH, state.category)
        assertEquals("100", state.inputValue)
        assertEquals(meterUnit, state.fromUnit)
        assertEquals(kilometerUnit, state.toUnit)
        assertEquals("0.1", state.result)
        assertEquals(units, state.availableUnits)
    }

    @Test
    fun `ConverterUiState equality works correctly`() {
        val state1 = ConverterUiState(
            inputValue = "100",
            fromUnit = meterUnit,
            toUnit = kilometerUnit
        )

        val state2 = ConverterUiState(
            inputValue = "100",
            fromUnit = meterUnit,
            toUnit = kilometerUnit
        )

        assertEquals(state1, state2)
    }

    @Test
    fun `ConverterUiState inequality works correctly`() {
        val state1 = ConverterUiState(inputValue = "100")
        val state2 = ConverterUiState(inputValue = "200")

        assertNotEquals(state1, state2)
    }

    @Test
    fun `ConverterUiState copy works correctly`() {
        val original = ConverterUiState(inputValue = "100")
        val copied = original.copy(inputValue = "200", result = "0.2")

        assertEquals("200", copied.inputValue)
        assertEquals("0.2", copied.result)
        assertEquals(original.category, copied.category)
    }

    @Test
    fun `ConverterUiState copy preserves unmodified fields`() {
        val original = ConverterUiState(
            inputValue = "100",
            fromUnit = meterUnit,
            toUnit = kilometerUnit
        )
        val copied = original.copy(result = "0.1")

        assertEquals(original.inputValue, copied.inputValue)
        assertEquals(original.fromUnit, copied.fromUnit)
        assertEquals(original.toUnit, copied.toUnit)
        assertEquals("0.1", copied.result)
    }

    @Test
    fun `ConverterUiState hashCode is consistent`() {
        val state1 = ConverterUiState(inputValue = "100")
        val state2 = ConverterUiState(inputValue = "100")

        assertEquals(state1.hashCode(), state2.hashCode())
    }
}
