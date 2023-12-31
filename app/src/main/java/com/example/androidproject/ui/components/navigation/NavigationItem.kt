package com.example.androidproject.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androidproject.R
import com.example.androidproject.ui.helpers.UiText

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: UiText.StringResource) {
    object Deals : NavigationItem("deals", Icons.Filled.AttachMoney, UiText.StringResource(R.string.deals_title))
    object Games : NavigationItem("games", Icons.Filled.VideogameAsset, UiText.StringResource(R.string.games_title))
    object Stores : NavigationItem("stores", Icons.Filled.ShoppingCart, UiText.StringResource(R.string.stores_title))
}