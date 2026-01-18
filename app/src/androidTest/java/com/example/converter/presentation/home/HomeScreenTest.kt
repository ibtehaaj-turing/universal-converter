package com.example.converter.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.presentation.components.CategoryCard
import com.example.converter.presentation.theme.ConverterTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysTitle() {
        composeTestRule.setContent {
            ConverterTheme {
                HomeScreenContent(
                    categories = listOf(ConverterCategory.LENGTH),
                    onCategoryClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Universal Converter").assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysLengthCategory() {
        composeTestRule.setContent {
            ConverterTheme {
                HomeScreenContent(
                    categories = listOf(ConverterCategory.LENGTH),
                    onCategoryClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Length").assertIsDisplayed()
    }

    @Test
    fun homeScreen_categoryCardClickCallsCallback() {
        var clickedCategory: ConverterCategory? = null

        composeTestRule.setContent {
            ConverterTheme {
                HomeScreenContent(
                    categories = listOf(ConverterCategory.LENGTH),
                    onCategoryClick = { clickedCategory = it }
                )
            }
        }

        composeTestRule.onNodeWithText("Length").performClick()

        assertEquals(ConverterCategory.LENGTH, clickedCategory)
    }

    @Test
    fun homeScreen_displaysEmptyStateWhenNoCategories() {
        composeTestRule.setContent {
            ConverterTheme {
                HomeScreenContent(
                    categories = emptyList(),
                    onCategoryClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Universal Converter").assertIsDisplayed()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    categories: List<ConverterCategory>,
    onCategoryClick: (ConverterCategory) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Universal Converter",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryCard(
                    category = category,
                    onClick = { onCategoryClick(category) }
                )
            }
        }
    }
}
