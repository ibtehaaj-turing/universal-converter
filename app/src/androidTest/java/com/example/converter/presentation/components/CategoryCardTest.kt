package com.example.converter.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.presentation.theme.ConverterTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CategoryCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun categoryCard_displaysLengthName() {
        composeTestRule.setContent {
            ConverterTheme {
                CategoryCard(
                    category = ConverterCategory.LENGTH,
                    onClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Length").assertIsDisplayed()
    }

    @Test
    fun categoryCard_displaysIcon() {
        composeTestRule.setContent {
            ConverterTheme {
                CategoryCard(
                    category = ConverterCategory.LENGTH,
                    onClick = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Length").assertIsDisplayed()
    }

    @Test
    fun categoryCard_clickCallsOnClick() {
        var clicked = false
        composeTestRule.setContent {
            ConverterTheme {
                CategoryCard(
                    category = ConverterCategory.LENGTH,
                    onClick = { clicked = true }
                )
            }
        }

        composeTestRule.onNodeWithText("Length").performClick()

        assertTrue(clicked)
    }

    @Test
    fun categoryCard_isClickable() {
        composeTestRule.setContent {
            ConverterTheme {
                CategoryCard(
                    category = ConverterCategory.LENGTH,
                    onClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Length").assertExists()
    }
}
