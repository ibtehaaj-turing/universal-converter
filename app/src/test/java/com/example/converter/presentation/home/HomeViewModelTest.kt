package com.example.converter.presentation.home

import app.cash.turbine.test
import com.example.converter.domain.model.ConverterCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state contains LENGTH category`() = runTest {
        val viewModel = HomeViewModel()

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(1, state.categories.size)
            assertTrue(state.categories.contains(ConverterCategory.LENGTH))
        }
    }

    @Test
    fun `uiState is exposed as StateFlow`() = runTest {
        val viewModel = HomeViewModel()

        val state = viewModel.uiState.value

        assertEquals(1, state.categories.size)
        assertTrue(state.categories.contains(ConverterCategory.LENGTH))
    }

    @Test
    fun `categories are loaded on init`() = runTest {
        val viewModel = HomeViewModel()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state.categories.isNotEmpty())
            assertEquals(ConverterCategory.LENGTH, state.categories[0])
        }
    }
}
