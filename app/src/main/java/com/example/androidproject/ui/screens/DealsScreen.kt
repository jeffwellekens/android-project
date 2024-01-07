package com.example.androidproject.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.androidproject.domain.model.Deal
import com.example.androidproject.ui.components.deal.DealItem
import com.example.androidproject.ui.theme.AppTheme
import com.example.androidproject.ui.viewmodels.StoreScreenViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun DealsScreen(
    deals: LazyPagingItems<Deal>,
    navigateTo: (String) -> Unit,
    modifier: Modifier? = Modifier,
    storeScreenViewModel: StoreScreenViewModel = hiltViewModel(),
) {

    Box(modifier = modifier!!.fillMaxSize()) {
        if (deals.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = deals.itemCount,
                    key = deals.itemKey { deal -> deal.dealID }) { index ->
                    index.let {
                        val deal = deals[it]
                        val storeName = storeScreenViewModel.uiState.value.stores?.find { store -> store.storeId == deal!!.storeID }?.storeName
                        if (deal != null) {
                            DealItem(
                                deal = deal,
                                storeName = storeName!!,
                                Modifier.clickable {
                                    navigateTo("dealDetail/${deal.dealID}")
                                }
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