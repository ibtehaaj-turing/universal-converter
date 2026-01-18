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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.presentation.components.ConversionInputField
import com.example.converter.presentation.components.SwapButton
import com.example.converter.presentation.components.UnitDropdown
import com.example.converter.presentation.theme.ConverterTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ConverterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

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

    private val units = listOf(meterUnit, kilometerUnit)

    @Test
    fun converterScreen_displaysTitle() {
        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Length Converter").assertIsDisplayed()
    }

    @Test
    fun converterScreen_displaysBackButton() {
        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Back").assertIsDisplayed()
    }

    @Test
    fun converterScreen_backButtonCallsCallback() {
        var backClicked = false

        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit
                    ),
                    onBackClick = { backClicked = true },
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        assertTrue(backClicked)
    }

    @Test
    fun converterScreen_displaysInputField() {
        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Enter value").assertIsDisplayed()
    }

    @Test
    fun converterScreen_displaysFromDropdown() {
        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithText("From").assertIsDisplayed()
    }

    @Test
    fun converterScreen_displaysToDropdown() {
        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithText("To").assertIsDisplayed()
    }

    @Test
    fun converterScreen_displaysSwapButton() {
        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Swap units").assertIsDisplayed()
    }

    @Test
    fun converterScreen_displaysResultCard() {
        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Result").assertIsDisplayed()
    }

    @Test
    fun converterScreen_displaysResultWithUnit() {
        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit,
                        result = "0.1"
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithText("0.1 km").assertIsDisplayed()
    }

    @Test
    fun converterScreen_displaysPlaceholderWhenNoResult() {
        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit,
                        result = ""
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = {}
                )
            }
        }

        composeTestRule.onNodeWithText("—").assertIsDisplayed()
    }

    @Test
    fun converterScreen_swapButtonCallsCallback() {
        var swapClicked = false

        composeTestRule.setContent {
            ConverterTheme {
                ConverterScreenContent(
                    uiState = ConverterUiState(
                        category = ConverterCategory.LENGTH,
                        availableUnits = units,
                        fromUnit = meterUnit,
                        toUnit = kilometerUnit
                    ),
                    onBackClick = {},
                    onInputValueChange = {},
                    onFromUnitChange = {},
                    onToUnitChange = {},
                    onSwapUnits = { swapClicked = true }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Swap units").performClick()

        assertTrue(swapClicked)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConverterScreenContent(
    uiState: ConverterUiState,
    onBackClick: () -> Unit,
    onInputValueChange: (String) -> Unit,
    onFromUnitChange: (ConversionUnit) -> Unit,
    onToUnitChange: (ConversionUnit) -> Unit,
    onSwapUnits: () -> Unit
) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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

            Card(
                modifier = Modifier.fillMaxWidth(),
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
                        text = if (uiState.result.isNotEmpty() && uiState.toUnit != null) {
                            "${uiState.result} ${uiState.toUnit.symbol}"
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
    }
}
