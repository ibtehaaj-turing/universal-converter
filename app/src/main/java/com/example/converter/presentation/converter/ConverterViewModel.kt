package com.example.converter.presentation.converter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.usecase.ConvertValueUseCase
import com.example.converter.domain.usecase.GetUnitsForCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUnitsForCategoryUseCase: GetUnitsForCategoryUseCase,
    private val convertValueUseCase: ConvertValueUseCase
) : ViewModel() {

    private val categoryName: String = savedStateHandle.get<String>("category") ?: "LENGTH"
    private val category: ConverterCategory = ConverterCategory.valueOf(categoryName)

    private val _uiState = MutableStateFlow(ConverterUiState(category = category))
    val uiState: StateFlow<ConverterUiState> = _uiState.asStateFlow()

    init {
        loadUnits()
    }

    private fun loadUnits() {
        val units = getUnitsForCategoryUseCase(category)
        _uiState.update { state ->
            state.copy(
                availableUnits = units,
                fromUnit = units.firstOrNull(),
                toUnit = units.getOrNull(1) ?: units.firstOrNull()
            )
        }
    }

    fun onInputValueChange(value: String) {
        _uiState.update { it.copy(inputValue = value) }
        performConversion()
    }

    fun onFromUnitChange(unit: ConversionUnit) {
        _uiState.update { it.copy(fromUnit = unit) }
        performConversion()
    }

    fun onToUnitChange(unit: ConversionUnit) {
        _uiState.update { it.copy(toUnit = unit) }
        performConversion()
    }

    fun onSwapUnits() {
        _uiState.update { state ->
            state.copy(
                fromUnit = state.toUnit,
                toUnit = state.fromUnit
            )
        }
        performConversion()
    }

    private fun performConversion() {
        val state = _uiState.value
        val inputValue = state.inputValue.toDoubleOrNull()
        val fromUnit = state.fromUnit
        val toUnit = state.toUnit

        if (inputValue != null && fromUnit != null && toUnit != null) {
            val result = convertValueUseCase(inputValue, fromUnit, toUnit)
            val formattedResult = formatResult(result.toValue)
            _uiState.update { it.copy(result = formattedResult) }
        } else {
            _uiState.update { it.copy(result = "") }
        }
    }

    private fun formatResult(value: Double): String {
        return if (value == value.toLong().toDouble()) {
            value.toLong().toString()
        } else {
            String.format("%.6f", value).trimEnd('0').trimEnd('.')
        }
    }
}
