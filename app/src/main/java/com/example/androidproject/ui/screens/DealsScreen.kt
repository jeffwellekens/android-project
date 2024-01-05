package com.example.androidproject.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.androidproject.domain.Deal
import com.example.androidproject.ui.components.deal.DealItem
import com.example.androidproject.ui.theme.AppTheme

@Composable
fun DealsScreen(
    deals: LazyPagingItems<Deal>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = deals.loadState) {
        if (deals.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (deals.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (deals.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = deals.itemCount,
                    key = deals.itemKey { deal -> deal.dealID }) { index ->
                    index.let {
                        val deal = deals[it]
                        if (deal != null) {
                            DealItem(
                                deal = deal,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                item {
                    if(deals.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DealsScreenPreview() {
    AppTheme {
        StoresScreen()
    }
}