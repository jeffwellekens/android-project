package com.example.androidproject.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import com.example.androidproject.domain.Deal
import com.example.androidproject.ui.screens.DealsScreen
import com.example.androidproject.ui.screens.GamesScreen
import com.example.androidproject.ui.screens.StoresScreen

@Composable
fun Navigation(navController: NavHostController, deals: LazyPagingItems<Deal>) {
    NavHost(navController, startDestination = NavigationItem.Deals.route) {
        composable(NavigationItem.Deals.route) {
            DealsScreen(deals = deals)
        }
        composable(NavigationItem.Games.route) {
            GamesScreen()
        }
        composable(NavigationItem.Stores.route) {
            StoresScreen()
        }
    }
}