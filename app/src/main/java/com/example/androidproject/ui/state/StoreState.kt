package com.example.androidproject.ui.state

import com.example.androidproject.domain.model.DealDetail
import com.example.androidproject.domain.model.Store

data class StoreState (
    val isRefreshing: Boolean = false,
    val stores: List<Store>?,
    val isError: Boolean = false,
)