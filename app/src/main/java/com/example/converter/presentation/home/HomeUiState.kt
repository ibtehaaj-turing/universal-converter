package com.example.converter.presentation.home

import com.example.converter.domain.model.ConverterCategory

data class HomeUiState(
    val categories: List<ConverterCategory> = listOf(ConverterCategory.LENGTH)
)
