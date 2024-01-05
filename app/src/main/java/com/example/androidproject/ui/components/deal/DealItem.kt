package com.example.androidproject.ui.components.deal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.androidproject.domain.Deal

@Composable
fun DealItem(
    deal: Deal,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) {
            AsyncImage(model = deal.thumb, contentDescription = "Image of the game", modifier = Modifier
                .weight(1f)
                .height(150.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(text = deal.title)
            }
        }
    }
}