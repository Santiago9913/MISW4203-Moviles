package com.grupo3.vinilos.collector.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.vinilos.collector.service.CollectorRepository
import com.grupo3.vinilos.utils.ERROR_MESSAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CollectorDetailViewModel : ViewModel() {
    private val repository = CollectorRepository()
    private val _state = MutableStateFlow(CollectorDetailState())
    val state: StateFlow<CollectorDetailState> = _state.asStateFlow()
    fun getCollector(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val collector = repository.getCollector(id)
                _state.update { currentState ->
                    currentState.copy(
                        collector = collector,
                    )
                }
            }
        } catch (e: Exception) {
            _state.update { currentState ->
                currentState.copy(
                    errorMessage = ERROR_MESSAGE
                )
            }

        }
    }
}