package com.example.converter.presentation.converter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.converter.domain.model.ConversionResult
import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.domain.usecase.ConvertValueUseCase
import com.example.converter.domain.usecase.GetUnitsForCategoryUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConverterViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var getUnitsForCategoryUseCase: GetUnitsForCategoryUseCase
    private lateinit var convertValueUseCase: ConvertValueUseCase

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

    private val lengthUnits = listOf(meterUnit, kilometerUnit, centimeterUnit)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        savedStateHandle = SavedStateHandle(mapOf("category" to "LENGTH"))
        getUnitsForCategoryUseCase = mockk()
        convertValueUseCase = mockk()

        every { getUnitsForCategoryUseCase(ConverterCategory.LENGTH) } returns lengthUnits
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel(): ConverterViewModel {
        return ConverterViewModel(
            savedStateHandle = savedStateHandle,
            getUnitsForCategoryUseCase = getUnitsForCategoryUseCase,
            convertValueUseCase = convertValueUseCase
        )
    }

    @Test
    fun `initial state loads units for category`() = runTest {
        val viewModel = createViewModel()

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(ConverterCategory.LENGTH, state.category)
            assertEquals(lengthUnits, state.availableUnits)
            assertEquals(meterUnit, state.fromUnit)
            assertEquals(kilometerUnit, state.toUnit)
        }
    }

    @Test
    fun `initial state sets first unit as fromUnit`() = runTest {
        val viewModel = createViewModel()

        val state = viewModel.uiState.value

        assertEquals(meterUnit, state.fromUnit)
    }

    @Test
    fun `initial state sets second unit as toUnit`() = runTest {
        val viewModel = createViewModel()

        val state = viewModel.uiState.value

        assertEquals(kilometerUnit, state.toUnit)
    }

    @Test
    fun `onInputValueChange updates input value`() = runTest {
        every { convertValueUseCase(100.0, meterUnit, kilometerUnit) } returns ConversionResult(
            fromValue = 100.0,
            fromUnit = meterUnit,
            toValue = 0.1,
            toUnit = kilometerUnit
        )
        val viewModel = createViewModel()

        viewModel.onInputValueChange("100")

        assertEquals("100", viewModel.uiState.value.inputValue)
    }

    @Test
    fun `onInputValueChange performs conversion with valid input`() = runTest {
        val conversionResult = ConversionResult(
            fromValue = 100.0,
            fromUnit = meterUnit,
            toValue = 0.1,
            toUnit = kilometerUnit
        )
        every { convertValueUseCase(100.0, meterUnit, kilometerUnit) } returns conversionResult

        val viewModel = createViewModel()
        viewModel.onInputValueChange("100")

        assertEquals("0.1", viewModel.uiState.value.result)
        verify { convertValueUseCase(100.0, meterUnit, kilometerUnit) }
    }

    @Test
    fun `onInputValueChange clears result with invalid input`() = runTest {
        val viewModel = createViewModel()

        viewModel.onInputValueChange("abc")

        assertEquals("", viewModel.uiState.value.result)
    }

    @Test
    fun `onInputValueChange clears result with empty input`() = runTest {
        val viewModel = createViewModel()

        viewModel.onInputValueChange("")

        assertEquals("", viewModel.uiState.value.result)
    }

    @Test
    fun `onFromUnitChange updates fromUnit`() = runTest {
        val viewModel = createViewModel()

        viewModel.onFromUnitChange(centimeterUnit)

        assertEquals(centimeterUnit, viewModel.uiState.value.fromUnit)
    }

    @Test
    fun `onFromUnitChange performs conversion`() = runTest {
        val conversionResult = ConversionResult(
            fromValue = 100.0,
            fromUnit = centimeterUnit,
            toValue = 0.001,
            toUnit = kilometerUnit
        )
        every { convertValueUseCase(100.0, meterUnit, kilometerUnit) } returns ConversionResult(
            fromValue = 100.0,
            fromUnit = meterUnit,
            toValue = 0.1,
            toUnit = kilometerUnit
        )
        every { convertValueUseCase(100.0, centimeterUnit, kilometerUnit) } returns conversionResult

        val viewModel = createViewModel()
        viewModel.onInputValueChange("100")
        viewModel.onFromUnitChange(centimeterUnit)

        assertEquals("0.001", viewModel.uiState.value.result)
    }

    @Test
    fun `onToUnitChange updates toUnit`() = runTest {
        val viewModel = createViewModel()

        viewModel.onToUnitChange(centimeterUnit)

        assertEquals(centimeterUnit, viewModel.uiState.value.toUnit)
    }

    @Test
    fun `onToUnitChange performs conversion`() = runTest {
        val conversionResult = ConversionResult(
            fromValue = 100.0,
            fromUnit = meterUnit,
            toValue = 10000.0,
            toUnit = centimeterUnit
        )
        every { convertValueUseCase(100.0, meterUnit, kilometerUnit) } returns ConversionResult(
            fromValue = 100.0,
            fromUnit = meterUnit,
            toValue = 0.1,
            toUnit = kilometerUnit
        )
        every { convertValueUseCase(100.0, meterUnit, centimeterUnit) } returns conversionResult

        val viewModel = createViewModel()
        viewModel.onInputValueChange("100")
        viewModel.onToUnitChange(centimeterUnit)

        assertEquals("10000", viewModel.uiState.value.result)
    }

    @Test
    fun `onSwapUnits swaps fromUnit and toUnit`() = runTest {
        val viewModel = createViewModel()

        val initialFromUnit = viewModel.uiState.value.fromUnit
        val initialToUnit = viewModel.uiState.value.toUnit

        viewModel.onSwapUnits()

        assertEquals(initialToUnit, viewModel.uiState.value.fromUnit)
        assertEquals(initialFromUnit, viewModel.uiState.value.toUnit)
    }

    @Test
    fun `onSwapUnits performs conversion after swap`() = runTest {
        val conversionResult = ConversionResult(
            fromValue = 100.0,
            fromUnit = kilometerUnit,
            toValue = 100000.0,
            toUnit = meterUnit
        )
        every { convertValueUseCase(100.0, meterUnit, kilometerUnit) } returns ConversionResult(
            fromValue = 100.0,
            fromUnit = meterUnit,
            toValue = 0.1,
            toUnit = kilometerUnit
        )
        every { convertValueUseCase(100.0, kilometerUnit, meterUnit) } returns conversionResult

        val viewModel = createViewModel()
        viewModel.onInputValueChange("100")
        viewModel.onSwapUnits()

        assertEquals("100000", viewModel.uiState.value.result)
    }

    @Test
    fun `formatResult removes trailing zeros`() = runTest {
        val conversionResult = ConversionResult(
            fromValue = 100.0,
            fromUnit = meterUnit,
            toValue = 0.1,
            toUnit = kilometerUnit
        )
        every { convertValueUseCase(100.0, meterUnit, kilometerUnit) } returns conversionResult

        val viewModel = createViewModel()
        viewModel.onInputValueChange("100")

        assertEquals("0.1", viewModel.uiState.value.result)
    }

    @Test
    fun `formatResult shows integer for whole numbers`() = runTest {
        val conversionResult = ConversionResult(
            fromValue = 1.0,
            fromUnit = kilometerUnit,
            toValue = 1000.0,
            toUnit = meterUnit
        )
        every { convertValueUseCase(1.0, meterUnit, kilometerUnit) } returns ConversionResult(
            fromValue = 1.0,
            fromUnit = meterUnit,
            toValue = 0.001,
            toUnit = kilometerUnit
        )
        every { convertValueUseCase(1.0, kilometerUnit, meterUnit) } returns conversionResult

        val viewModel = createViewModel()
        viewModel.onSwapUnits()
        viewModel.onInputValueChange("1")

        assertEquals("1000", viewModel.uiState.value.result)
    }

    @Test
    fun `default category is LENGTH when not provided in savedStateHandle`() = runTest {
        savedStateHandle = SavedStateHandle()
        every { getUnitsForCategoryUseCase(ConverterCategory.LENGTH) } returns lengthUnits

        val viewModel = createViewModel()

        assertEquals(ConverterCategory.LENGTH, viewModel.uiState.value.category)
    }

    @Test
    fun `units are loaded from use case on init`() = runTest {
        createViewModel()

        verify(exactly = 1) { getUnitsForCategoryUseCase(ConverterCategory.LENGTH) }
    }

    @Test
    fun `state availableUnits is not empty after init`() = runTest {
        val viewModel = createViewModel()

        val state = viewModel.uiState.value

        assertEquals(3, state.availableUnits.size)
    }

    @Test
    fun `fromUnit and toUnit are not null after init`() = runTest {
        val viewModel = createViewModel()

        val state = viewModel.uiState.value

        assertNotNull(state.fromUnit)
        assertNotNull(state.toUnit)
    }

    @Test
    fun `handles single unit in list`() = runTest {
        every { getUnitsForCategoryUseCase(ConverterCategory.LENGTH) } returns listOf(meterUnit)

        val viewModel = createViewModel()

        val state = viewModel.uiState.value
        assertEquals(meterUnit, state.fromUnit)
        assertEquals(meterUnit, state.toUnit)
    }

    @Test
    fun `handles empty units list`() = runTest {
        every { getUnitsForCategoryUseCase(ConverterCategory.LENGTH) } returns emptyList()

        val viewModel = createViewModel()

        val state = viewModel.uiState.value
        assertEquals(null, state.fromUnit)
        assertEquals(null, state.toUnit)
    }
}
