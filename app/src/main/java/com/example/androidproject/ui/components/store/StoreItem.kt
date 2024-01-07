package com.example.androidproject.ui.components.store

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.androidproject.domain.model.Store

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreItem(store: Store, modifier: Modifier? = Modifier) {
    ListItem(
        modifier = modifier!!,
        headlineText = { Text(store.storeName) },
        leadingContent = {
            AsyncImage(
                modifier = Modifier
                    .height(56.dp)
                    .width(56.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                model = "https://www.cheapshark.com/${store.images.logo}",
                contentDescription = "Store logo"
            )

        },
    )
    Divider()
}