package com.example.androidproject.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.data.repositories.DealRepository
import com.example.androidproject.domain.model.DealDetail
import com.example.androidproject.ui.state.UIState
import com.example.androidproject.ui.state.DealDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealsDetailScreenViewModel @Inject constructor(
    val dealRepository: DealRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DealDetailState(false, null))
    val uiState: StateFlow<DealDetailState> = _uiState.asStateFlow()
    var dealIdState = mutableStateOf("")
        private set
    var dealApiState: UIState<DealDetail> by mutableStateOf(UIState.Loading())
        private set

    private val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        exception.printStackTrace()
        showError()
    }

    init {
        val argument = savedStateHandle.get<String>("id").orEmpty()
        dealIdState.value = argument
        refreshDealDetail()
    }
    private fun getDeal() {
        viewModelScope.launch {
            dealRepository.getDealByDealId(dealIdState.value)
                .catch { exception ->
                    dealApiState = UIState.Error(exception.message)
                    exception.printStackTrace()
                }
                .collect { dealDetail ->
                    dealApiState = UIState.Success(dealDetail)
                    _uiState.update {
                        it.copy(deal = dealDetail)
                    }
                }
        }
    }

    fun refreshDealDetail() {
        _uiState.update {
            it.copy(isRefreshing = true)
        }
        viewModelScope.launch(exceptionHandler) {
            val refreshDealDetailDeferred =
                async { dealRepository.insertDealDetailByDealId(dealIdState.value) }
            try {
                awaitAll(refreshDealDetailDeferred)
            } finally {
                _uiState.update {
                    it.copy(isRefreshing = false)
                }
                getDeal()
            }
        }
    }

    private fun showError() {
        _uiState.update {
            it.copy(isError = true)
        }
    }

    fun onErrorConsumed() {
        _uiState.update {
            it.copy(isError = false)
        }
    }
}