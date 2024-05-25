package com.grupo3.vinilos.artists.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.vinilos.artists.service.ArtistsRepository
import com.grupo3.vinilos.utils.ERROR_MESSAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtistsViewModel : ViewModel() {
    private val repository = ArtistsRepository()

    private val _state = MutableStateFlow(ArtistsListState())
    val state: StateFlow<ArtistsListState> = _state.asStateFlow()

    fun getArtists() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val bands = repository.getBands()
                val musicians = repository.getMusicians()
                var artists = bands + musicians
                artists = artists.sortedBy { it.name }
                _state.update { currentState ->
                    currentState.copy(
                        artists = artists,
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