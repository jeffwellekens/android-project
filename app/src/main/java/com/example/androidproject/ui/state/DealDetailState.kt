package com.example.androidproject.ui.state

import com.example.androidproject.domain.model.DealDetail

data class DealDetailState(
    val isRefreshing: Boolean = false,
    val deal: DealDetail?,
    val isError: Boolean = false,
)
