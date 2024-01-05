package com.example.androidproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.androidproject.data.local.DealEntity
import com.example.androidproject.data.mappers.toDeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DealViewModel @Inject constructor(
    pager: Pager<Int, DealEntity>
) : ViewModel() {
    val dealPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map {it.toDeal()}
        }
        .cachedIn(viewModelScope)
}