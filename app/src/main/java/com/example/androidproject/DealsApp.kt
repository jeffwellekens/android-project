package com.example.androidproject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.androidproject.ui.components.search.SearchView
import com.example.androidproject.ui.components.navigation.BottomNavigationBar
import com.example.androidproject.ui.components.navigation.Navigation
import com.example.androidproject.ui.theme.AppTheme
import com.example.androidproject.ui.viewmodels.DealsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealsApp(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val dealsScreenViewModel = hiltViewModel<DealsScreenViewModel>()
    val deals = dealsScreenViewModel.dealPagingFlow.collectAsLazyPagingItems()
    val currentSearchQuery = remember { mutableStateOf("") }
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
    val snackbarHostState = remember { SnackbarHostState() }
    CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
        Scaffold(
            topBar = {
                AnimatedVisibility(
                    visible = (navBackStackEntry?.destination?.route == "deals" || navBackStackEntry?.destination?.route?.startsWith(
                        "dealDetail"
                    ) == true),
                    enter = fadeIn(tween(700)),
                    exit = fadeOut(tween(700))
                ) {
                    if (navBackStackEntry?.destination?.route == "deals") {
                        SearchView(
                            query = dealsScreenViewModel.query.value,
                            onQueryChanged = { dealsScreenViewModel.query.value = it },
                            onClearQuery = {
                                if (currentSearchQuery.value == "") {
                                    dealsScreenViewModel.setQuery("")
                                } else {
                                    dealsScreenViewModel.setQuery("")
                                    deals.refresh()
                                }
                            },
                            onSearch = {
                                currentSearchQuery.value = dealsScreenViewModel.query.value
                                deals.refresh()
                                focusManager.clearFocus()
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        TopAppBar(title = { Text("Deal") },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back arrow")
                                }
                            })
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(
                    navBackStackEntry,
                    ::navigate,
                    Modifier.testTag("navigation")
                )
            },
            containerColor = MaterialTheme.colorScheme.background,
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) {
            Column {
                Navigation(
                    navController = navController,
                    deals = deals,
                    modifier = Modifier.padding(paddingValues = it)
                )
            }
        }
    }

}

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    error("No Snackbar Host State provided")
}