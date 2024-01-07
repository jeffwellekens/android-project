package com.example.androidproject.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import com.example.androidproject.domain.model.Deal
import com.example.androidproject.ui.screens.DealDetailScreen
import com.example.androidproject.ui.screens.DealsScreen
import com.example.androidproject.ui.screens.StoresScreen

@Composable
fun Navigation(navController: NavHostController, deals: LazyPagingItems<Deal>, modifier: Modifier? = Modifier) {
    NavHost(navController, startDestination = BottomNavigationItem.Deals.route) {
        composable(BottomNavigationItem.Deals.route) {
            DealsScreen(deals = deals, navController::navigate, modifier)
        }
        composable(BottomNavigationItem.Stores.route) {
            StoresScreen()
        }
        composable(
            "dealDetail/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            val id = backStackEntry.arguments!!.getString("id")
            DealDetailScreen(id!!, modifier)
        }
    }
}