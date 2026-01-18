package com.example.converter.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.example.converter.presentation.theme.ConverterTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SwapButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun swapButton_displaysIcon() {
        composeTestRule.setContent {
            ConverterTheme {
                SwapButton(onClick = {})
            }
        }

        composeTestRule.onNodeWithContentDescription("Swap units").assertIsDisplayed()
    }

    @Test
    fun swapButton_clickCallsOnClick() {
        var clicked = false

        composeTestRule.setContent {
            ConverterTheme {
                SwapButton(onClick = { clicked = true })
            }
        }

        composeTestRule.onNodeWithContentDescription("Swap units").performClick()

        assertTrue(clicked)
    }

    @Test
    fun swapButton_isClickable() {
        composeTestRule.setContent {
            ConverterTheme {
                SwapButton(onClick = {})
            }
        }

        composeTestRule.onNodeWithContentDescription("Swap units").assertExists()
    }

    @Test
    fun swapButton_multipleClicks() {
        var clickCount = 0

        composeTestRule.setContent {
            ConverterTheme {
                SwapButton(onClick = { clickCount++ })
            }
        }

        composeTestRule.onNodeWithContentDescription("Swap units").performClick()
        composeTestRule.onNodeWithContentDescription("Swap units").performClick()
        composeTestRule.onNodeWithContentDescription("Swap units").performClick()

        assertTrue(clickCount == 3)
    }
}
