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

@Composable
fun BottomNavigationBar(
    navBackStackEntry: NavBackStackEntry?,
    navigate: (String) -> Unit,
    modifier: Modifier? = Modifier
) {
    val items = listOf(
        BottomNavigationItem.Deals,
        BottomNavigationItem.Stores
    )
    NavigationBar(modifier = modifier!!) {
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                modifier = Modifier.testTag(item.title.asString()),
                selected = (currentRoute == item.route || (item.route == "deals" && currentRoute?.startsWith("dealDetail") == true)),
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = "navigate to ${item.title.asString()}"
                    )
                },
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