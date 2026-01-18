package com.example.converter.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ConverterCategoryTest {

    @Test
    fun `LENGTH category exists`() {
        val category = ConverterCategory.LENGTH
        assertNotNull(category)
        assertEquals("LENGTH", category.name)
    }

    @Test
    fun `ConverterCategory values returns all categories`() {
        val values = ConverterCategory.values()
        assertEquals(1, values.size)
        assertEquals(ConverterCategory.LENGTH, values[0])
    }

    @Test
    fun `ConverterCategory valueOf returns correct category`() {
        val category = ConverterCategory.valueOf("LENGTH")
        assertEquals(ConverterCategory.LENGTH, category)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ConverterCategory valueOf throws exception for invalid name`() {
        ConverterCategory.valueOf("INVALID")
    }

    @Test
    fun `ConverterCategory ordinal is correct`() {
        assertEquals(0, ConverterCategory.LENGTH.ordinal)
    }
}
