package com.example.androidproject

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.androidproject.ui.components.navigation.NavigationItem
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DealsAppNavigationTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupDealsAppNavHost() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            DealsApp(navController = navController)
        }
        /**
         * Makes sure [navController] is initialized when we get to the tests.
         */
        composeTestRule.onNodeWithTag("navigation").assertExists()
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