package com.example.androidproject.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.data.repositories.StoreRepository
import com.example.androidproject.domain.model.DealDetail
import com.example.androidproject.domain.model.Store
import com.example.androidproject.ui.state.DealDetailState
import com.example.androidproject.ui.state.StoreState
import com.example.androidproject.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreScreenViewModel @Inject constructor(
    val storeRepository: StoreRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(StoreState(false, null))
    val uiState: StateFlow<StoreState> = _uiState.asStateFlow()


    var storeApiState: UIState<List<Store>> by mutableStateOf(UIState.Loading())
        private set

    private val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        exception.printStackTrace()
        showError()
    }

    init {
        refreshStores()
        getStores()
    }

    fun getStores() {
        viewModelScope.launch {
            storeRepository.getAll()
                .catch { exception ->
                    storeApiState = UIState.Error(exception.message)
                    exception.printStackTrace()
                }
                .collect { stores ->
                    storeApiState = UIState.Success(stores)
                    _uiState.update {
                        it.copy(stores = stores)
                    }

                }
        }
    }

    fun refreshStores() {
        _uiState.update {
            it.copy(isRefreshing = true)
        }
        viewModelScope.launch(exceptionHandler) {
            val refreshStoresDeferred =
                async { storeRepository.refreshStores() }
            try {
                awaitAll(refreshStoresDeferred)
            } finally {
                _uiState.update {
                    it.copy(isRefreshing = false)
                }
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
