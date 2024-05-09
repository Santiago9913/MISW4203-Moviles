package com.grupo3.vinilos.artists.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.vinilos.artists.service.ArtistsRepository
import com.grupo3.vinilos.utils.ERROR_MESSAGE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

class ArtistDetailViewModel : ViewModel() {
    private val repository = ArtistsRepository();
    private val _state = MutableStateFlow(ArtistDetailState());
    val state: StateFlow<ArtistDetailState> = _state.asStateFlow()
    fun getArtist(id: Int) {
        viewModelScope.launch {
            try {
                val artist = repository.getMusician(id)
                _state.update { currentState ->
                    currentState.copy(
                        artist = artist,
                    )
                }
            } catch (e: Exception) {
                try {
                    val artist = repository.getBand(id)
                    _state.update { currentState ->
                        currentState.copy(
                            artist = artist,
                        )
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
    }
}