package com.example.converter.presentation.home

import com.example.converter.domain.model.ConverterCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeUiStateTest {

    @Test
    fun `default HomeUiState contains LENGTH category`() {
        val state = HomeUiState()

        assertEquals(1, state.categories.size)
        assertTrue(state.categories.contains(ConverterCategory.LENGTH))
    }

    @Test
    fun `HomeUiState can be created with custom categories`() {
        val categories = listOf(ConverterCategory.LENGTH)
        val state = HomeUiState(categories = categories)

        assertEquals(categories, state.categories)
    }

    @Test
    fun `HomeUiState equality works correctly`() {
        val state1 = HomeUiState()
        val state2 = HomeUiState()

        assertEquals(state1, state2)
    }

    @Test
    fun `HomeUiState copy works correctly`() {
        val original = HomeUiState()
        val copied = original.copy(categories = emptyList())

        assertTrue(copied.categories.isEmpty())
        assertNotEquals(original, copied)
    }

    @Test
    fun `HomeUiState hashCode is consistent`() {
        val state1 = HomeUiState()
        val state2 = HomeUiState()

        assertEquals(state1.hashCode(), state2.hashCode())
    }
}
