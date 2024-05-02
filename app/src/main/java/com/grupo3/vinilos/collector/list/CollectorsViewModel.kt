package com.grupo3.vinilos.collector.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.vinilos.collector.service.CollectorRepository
import com.grupo3.vinilos.utils.ERROR_MESSAGE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CollectorsViewModel: ViewModel() {
    private val repository = CollectorRepository()

    private val _state = MutableStateFlow(CollectorsListState())
    val state: StateFlow<CollectorsListState> = _state.asStateFlow()

    fun getCollectors() {
        viewModelScope.launch {
            try {
                val collectors = repository.getCollectors()

                _state.update {
                    currentState -> currentState.copy(
                        collectors = collectors,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    currentState -> currentState.copy(
                        errorMessage = ERROR_MESSAGE
                    )
                }
            }
        }
    }
}