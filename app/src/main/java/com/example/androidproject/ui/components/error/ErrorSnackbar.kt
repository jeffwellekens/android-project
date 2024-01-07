package com.example.androidproject.ui.components.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.androidproject.LocalSnackbarHostState
import com.example.androidproject.R

@Composable
fun ErrorSnackbar(isError: Boolean, onErrorConsumed: () -> Unit) {
    val snackbarHostState = LocalSnackbarHostState.current
    val context = LocalContext.current
    val errorMessage = context.getString(R.string.error_text)
    val okText = context.getString(R.string.ok)

    if (isError) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
                actionLabel = okText,
            )
            onErrorConsumed()
        }
    }
}