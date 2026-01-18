package com.example.converter.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.converter.presentation.theme.ConverterTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ConversionInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun conversionInputField_displaysLabel() {
        composeTestRule.setContent {
            ConverterTheme {
                ConversionInputField(
                    value = "",
                    onValueChange = {},
                    label = "Enter value"
                )
            }
        }

        composeTestRule.onNodeWithText("Enter value").assertIsDisplayed()
    }

    @Test
    fun conversionInputField_displaysValue() {
        composeTestRule.setContent {
            ConverterTheme {
                ConversionInputField(
                    value = "100",
                    onValueChange = {},
                    label = "Enter value"
                )
            }
        }

        composeTestRule.onNodeWithText("100").assertIsDisplayed()
    }

    @Test
    fun conversionInputField_acceptsNumericInput() {
        var inputValue by mutableStateOf("")

        composeTestRule.setContent {
            ConverterTheme {
                ConversionInputField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = "Enter value"
                )
            }
        }

        composeTestRule.onNodeWithText("Enter value").performTextInput("123")

        assertEquals("123", inputValue)
    }

    @Test
    fun conversionInputField_acceptsDecimalInput() {
        var inputValue by mutableStateOf("")

        composeTestRule.setContent {
            ConverterTheme {
                ConversionInputField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = "Enter value"
                )
            }
        }

        composeTestRule.onNodeWithText("Enter value").performTextInput("12.5")

        assertEquals("12.5", inputValue)
    }

    @Test
    fun conversionInputField_rejectsNonNumericInput() {
        var inputValue by mutableStateOf("")

        composeTestRule.setContent {
            ConverterTheme {
                ConversionInputField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = "Enter value"
                )
            }
        }

        composeTestRule.onNodeWithText("Enter value").performTextInput("abc")

        assertEquals("", inputValue)
    }

    @Test
    fun conversionInputField_acceptsEmptyInput() {
        var inputValue by mutableStateOf("100")

        composeTestRule.setContent {
            ConverterTheme {
                ConversionInputField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = "Enter value"
                )
            }
        }

        composeTestRule.onNodeWithText("100").performTextClearance()

        assertEquals("", inputValue)
    }

    @Test
    fun conversionInputField_acceptsSingleDecimalPoint() {
        var inputValue by mutableStateOf("")

        composeTestRule.setContent {
            ConverterTheme {
                ConversionInputField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = "Enter value"
                )
            }
        }

        composeTestRule.onNodeWithText("Enter value").performTextInput(".")

        assertEquals(".", inputValue)
    }
}
