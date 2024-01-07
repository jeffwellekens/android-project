package com.example.androidproject.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androidproject.R
import com.example.androidproject.ui.helpers.UiText

sealed class BottomNavigationItem(var route: String, var icon: ImageVector, var title: UiText.StringResource) {
    object Deals : BottomNavigationItem("deals", Icons.Filled.AttachMoney, UiText.StringResource(R.string.deals_title))
    object Stores : BottomNavigationItem("stores", Icons.Filled.ShoppingCart, UiText.StringResource(R.string.stores_title))
}