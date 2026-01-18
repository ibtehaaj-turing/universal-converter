package com.example.converter.presentation.converter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.presentation.components.ConversionInputField
import com.example.converter.presentation.components.SwapButton
import com.example.converter.presentation.components.UnitDropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen(
    onBackClick: () -> Unit,
    viewModel: ConverterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val title = when (uiState.category) {
        ConverterCategory.LENGTH -> "Length Converter"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        ConverterContent(
            uiState = uiState,
            onInputValueChange = viewModel::onInputValueChange,
            onFromUnitChange = viewModel::onFromUnitChange,
            onToUnitChange = viewModel::onToUnitChange,
            onSwapUnits = viewModel::onSwapUnits,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun ConverterContent(
    uiState: ConverterUiState,
    onInputValueChange: (String) -> Unit,
    onFromUnitChange: (com.example.converter.domain.model.ConversionUnit) -> Unit,
    onToUnitChange: (com.example.converter.domain.model.ConversionUnit) -> Unit,
    onSwapUnits: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ConversionInputField(
            value = uiState.inputValue,
            onValueChange = onInputValueChange,
            label = "Enter value"
        )

        UnitDropdown(
            selectedUnit = uiState.fromUnit,
            units = uiState.availableUnits,
            onUnitSelected = onFromUnitChange,
            label = "From"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SwapButton(onClick = onSwapUnits)
        }

        UnitDropdown(
            selectedUnit = uiState.toUnit,
            units = uiState.availableUnits,
            onUnitSelected = onToUnitChange,
            label = "To"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ResultCard(
            result = uiState.result,
            toUnit = uiState.toUnit
        )
    }
}

@Composable
private fun ResultCard(
    result: String,
    toUnit: com.example.converter.domain.model.ConversionUnit?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Result",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (result.isNotEmpty() && toUnit != null) {
                    "$result ${toUnit.symbol}"
                } else {
                    "—"
                },
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}
