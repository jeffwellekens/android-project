package com.example.androidproject.ui.components.deal

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
import com.example.androidproject.domain.Deal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealItem(deal: Deal) {
    ListItem(
        headlineText = { Text(deal.title) },
        overlineText = { Text("Steam") },
        supportingText = { Text("Deal rating: ${deal.dealRating}") },
        leadingContent = {
            AsyncImage(
                modifier = Modifier
                    .height(56.dp)
                    .width(56.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                model = deal.thumb,
                contentDescription = "Game thumbnail"
            )

        },
        trailingContent = {
            Column {
                Text(deal.normalPrice, textDecoration = TextDecoration.LineThrough)
                Text(deal.salePrice)
            }
        }
    )
    Divider()
}