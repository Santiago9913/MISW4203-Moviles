package com.grupo3.vinilos.artists.detail

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

class ArtistDetailViewModel : ViewModel() {
    private val repository = ArtistsRepository()
    private val _state = MutableStateFlow(ArtistDetailState())
    val state: StateFlow<ArtistDetailState> = _state.asStateFlow()
    fun getArtist(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val musician = try {
                repository.getMusician(id)
            } catch (e: Exception) {
                null
            }
            val band = try {
                repository.getBand(id)
            } catch (e: Exception) {
                null
            }

            _state.update { currentState ->
                currentState.copy(
                    artist = musician ?: band,
                    errorMessage = if (musician == null && band == null) ERROR_MESSAGE else currentState.errorMessage
                )
            }
        }
    }
}