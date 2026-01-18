package com.example.converter.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.converter.domain.model.ConversionUnit
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.presentation.theme.ConverterTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class UnitDropdownTest {

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

    private val centimeterUnit = ConversionUnit(
        id = "cm",
        name = "Centimeter",
        symbol = "cm",
        category = ConverterCategory.LENGTH,
        toBaseFactor = 0.01
    )

    private val units = listOf(meterUnit, kilometerUnit, centimeterUnit)

    @Test
    fun unitDropdown_displaysLabel() {
        composeTestRule.setContent {
            ConverterTheme {
                UnitDropdown(
                    selectedUnit = null,
                    units = units,
                    onUnitSelected = {},
                    label = "From"
                )
            }
        }

        composeTestRule.onNodeWithText("From").assertIsDisplayed()
    }

    @Test
    fun unitDropdown_displaysSelectedUnit() {
        composeTestRule.setContent {
            ConverterTheme {
                UnitDropdown(
                    selectedUnit = meterUnit,
                    units = units,
                    onUnitSelected = {},
                    label = "From"
                )
            }
        }

        composeTestRule.onNodeWithText("Meter (m)").assertIsDisplayed()
    }

    @Test
    fun unitDropdown_showsDropdownOnClick() {
        composeTestRule.setContent {
            ConverterTheme {
                UnitDropdown(
                    selectedUnit = meterUnit,
                    units = units,
                    onUnitSelected = {},
                    label = "From"
                )
            }
        }

        composeTestRule.onNodeWithText("Meter (m)").performClick()

        composeTestRule.onNodeWithText("Kilometer (km)").assertIsDisplayed()
        composeTestRule.onNodeWithText("Centimeter (cm)").assertIsDisplayed()
    }

    @Test
    fun unitDropdown_selectsUnit() {
        var selectedUnit: ConversionUnit? = meterUnit

        composeTestRule.setContent {
            ConverterTheme {
                UnitDropdown(
                    selectedUnit = selectedUnit,
                    units = units,
                    onUnitSelected = { selectedUnit = it },
                    label = "From"
                )
            }
        }

        composeTestRule.onNodeWithText("Meter (m)").performClick()
        composeTestRule.onNodeWithText("Kilometer (km)").performClick()

        assertEquals(kilometerUnit, selectedUnit)
    }

    @Test
    fun unitDropdown_showsAllUnitsInDropdown() {
        composeTestRule.setContent {
            ConverterTheme {
                UnitDropdown(
                    selectedUnit = meterUnit,
                    units = units,
                    onUnitSelected = {},
                    label = "From"
                )
            }
        }

        composeTestRule.onNodeWithText("Meter (m)").performClick()

        composeTestRule.onNodeWithText("Meter (m)").assertExists()
        composeTestRule.onNodeWithText("Kilometer (km)").assertExists()
        composeTestRule.onNodeWithText("Centimeter (cm)").assertExists()
    }

    @Test
    fun unitDropdown_emptyWhenNoUnitSelected() {
        composeTestRule.setContent {
            ConverterTheme {
                UnitDropdown(
                    selectedUnit = null,
                    units = units,
                    onUnitSelected = {},
                    label = "From"
                )
            }
        }

        composeTestRule.onNodeWithText("From").assertIsDisplayed()
    }

    @Test
    fun unitDropdown_handlesEmptyUnitsList() {
        composeTestRule.setContent {
            ConverterTheme {
                UnitDropdown(
                    selectedUnit = null,
                    units = emptyList(),
                    onUnitSelected = {},
                    label = "From"
                )
            }
        }

        composeTestRule.onNodeWithText("From").assertIsDisplayed()
    }
}
