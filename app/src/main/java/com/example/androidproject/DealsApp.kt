package com.example.androidproject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.androidproject.ui.components.SearchView
import com.example.androidproject.ui.components.navigation.BottomNavigationBar
import com.example.androidproject.ui.components.navigation.Navigation
import com.example.androidproject.ui.theme.AppTheme
import com.example.androidproject.ui.viewmodels.DealViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealsApp(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val dealViewModel = hiltViewModel<DealViewModel>()
    val deals = dealViewModel.dealPagingFlow.collectAsLazyPagingItems()
    fun navigate(route: String) {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            if (navBackStackEntry?.destination?.route == "deals") {
                SearchView(
                    query = dealViewModel.query.value,
                    onQueryChanged = { dealViewModel.query.value = it },
                    onClearQuery = {
                        dealViewModel.setQuery("")
                        deals.refresh()
                    },
                    onSearch = {
                        deals.refresh()
                        focusManager.clearFocus()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        bottomBar = { BottomNavigationBar(navBackStackEntry, ::navigate) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(paddingValues = it)
        ) {
            Navigation(navController = navController, deals = deals)
        }
    }
}


@Preview
@Composable
fun DealsAppPreview() {
    AppTheme {
        DealsApp()
    }
}