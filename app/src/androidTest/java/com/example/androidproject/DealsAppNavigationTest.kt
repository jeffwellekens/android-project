package com.example.androidproject

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.androidproject.ui.components.navigation.NavigationItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DealsAppNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupDealsAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            DealsApp(navController = navController)
        }
    }

    @Test
    fun dealsAppNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(NavigationItem.Deals.route)
    }

    @Test
    fun dealsAppNavHost_clickOnGamesIcon_navigateToGamesScreen() {
        composeTestRule.onNodeWithStringIdTag(R.string.games_title).performClick()
        navController.assertCurrentRouteName(NavigationItem.Games.route)
    }

    @Test
    fun dealsAppNavHost_clickOnStoresIcon_navigateToGamesScreen() {
        composeTestRule.onNodeWithStringIdTag(R.string.stores_title).performClick()
        navController.assertCurrentRouteName(NavigationItem.Stores.route)
    }
}