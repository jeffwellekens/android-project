package com.example.androidproject.navigationtests

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.androidproject.DealsApp
import com.example.androidproject.MainActivity
import com.example.androidproject.R
import com.example.androidproject.onNodeWithStringIdTag
import com.example.androidproject.ui.components.navigation.BottomNavigationItem
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
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
    }

    @Test
    fun dealsAppNavHost_verifyStartDestination() {
        /**
         * Makes sure [navController] is initialized when we get to the tests.
         */
        composeTestRule.onNodeWithTag("navigation").assertExists()

        navController.assertCurrentRouteName(BottomNavigationItem.Deals.route)
    }

    @Test
    fun dealsAppNavHost_clickOnStoresIcon_navigateToGamesScreen() {
        composeTestRule.onNodeWithStringIdTag(R.string.stores_title).performClick()
        navController.assertCurrentRouteName(BottomNavigationItem.Stores.route)
    }
}