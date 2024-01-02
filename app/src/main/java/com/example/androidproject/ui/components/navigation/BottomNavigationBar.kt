package com.example.androidproject.ui.components.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import com.example.androidproject.ui.theme.AppTheme

@Composable
fun BottomNavigationBar(navBackStackEntry: NavBackStackEntry?, navigate: (String) -> Unit) {
    val items = listOf(
        NavigationItem.Deals,
        NavigationItem.Games,
        NavigationItem.Stores
    )
    NavigationBar {
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                modifier = Modifier.testTag(item.title.asString()) ,
                selected = currentRoute == item.route,
                icon = { Icon(item.icon, contentDescription = "navigate to ${item.title.asString()}") },
                label = { Text(text = item.title.asString()) },
                onClick = {
                          navigate(item.route)
                },
            )
        }
    }
}

@Preview
@Composable
fun NavbarPreview() {
//    AppTheme {
//        BottomNavigationBar()
//    }
}