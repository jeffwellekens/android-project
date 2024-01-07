package com.example.androidproject.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.androidproject.data.repositories.OfflineDealRepository
import com.example.androidproject.data.mappers.toDeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DealsScreenViewModel @Inject constructor(
    offlineDealRepository: OfflineDealRepository
) : ViewModel() {
    var query = mutableStateOf("")
    fun setQuery(query: String) {
        this.query.value = query
    }

    val dealPagingFlow = offlineDealRepository.getDeals(query).map { pagingData ->
        pagingData.map { it.toDeal() }

    }.cachedIn(viewModelScope)



}