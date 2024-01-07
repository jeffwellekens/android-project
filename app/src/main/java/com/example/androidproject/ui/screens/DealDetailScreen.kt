package com.example.androidproject.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.androidproject.ui.components.error.ErrorSnackbar
import com.example.androidproject.ui.state.UIState
import com.example.androidproject.ui.viewmodels.DealsDetailScreenViewModel

@Composable
fun DealDetailScreen(
    dealId: String,
    modifier: Modifier? = Modifier,
    dealsDetailScreenViewModel: DealsDetailScreenViewModel = hiltViewModel()
) {
    val dealState by dealsDetailScreenViewModel.uiState.collectAsState()
    val dealApiState = dealsDetailScreenViewModel.dealApiState

    ErrorSnackbar(
        isError = dealState.isError,
        onErrorConsumed = dealsDetailScreenViewModel::onErrorConsumed
    )
    when (dealApiState) {
        is UIState.Loading -> Box(modifier = modifier!!.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        is UIState.Error -> Column(modifier = modifier!!) {
            Text(dealApiState.error!!)
        }

        is UIState.Success -> {
            val dealDetail = dealState.deal!!
            Column(modifier = modifier!!.fillMaxWidth()) {
                AsyncImage(
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                    model = dealDetail.gameInfo.thumb,
                    contentDescription = "Game thumbnail"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = dealDetail.gameInfo.name, style = MaterialTheme.typography.titleLarge)
            }
        }
    }

}