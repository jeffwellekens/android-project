package com.example.androidproject.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidproject.ui.components.store.StoreItem
import com.example.androidproject.ui.state.UIState
import com.example.androidproject.ui.theme.AppTheme
import com.example.androidproject.ui.viewmodels.StoreScreenViewModel

@Composable
fun StoresScreen(
    storeScreenViewModel: StoreScreenViewModel = hiltViewModel(),
    modifier: Modifier? = Modifier
) {
    val storeState by storeScreenViewModel.uiState.collectAsState()
    val storeApiState = storeScreenViewModel.storeApiState

    Box(modifier = modifier!!.fillMaxSize()) {
        when (storeApiState) {
            is UIState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

            is UIState.Error -> Text(storeApiState.error!!)
            is UIState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(storeState.stores!!) {
                        StoreItem(it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoresScreenPreview() {
    AppTheme {
        StoresScreen()
    }
}