package com.example.androidproject.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidproject.ui.views.DealsScreen
import com.example.androidproject.ui.views.GamesScreen
import com.example.androidproject.ui.views.StoresScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Deals.route) {
        composable(NavigationItem.Deals.route) {
            DealsScreen()
        }
        composable(NavigationItem.Games.route) {
            GamesScreen()
        }
        composable(NavigationItem.Stores.route) {
            StoresScreen()
        }
    }
}