package com.grupo3.vinilos.artists.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.vinilos.artists.service.ArtistsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

class ArtistsViewModel : ViewModel() {
    private val repository = ArtistsRepository();

    private val _state = MutableStateFlow(ArtistsListState());
    val state: StateFlow<ArtistsListState> = _state.asStateFlow()

    fun getArtists() {
        viewModelScope.launch {
            try {
                val bands = repository.getBands();
                val musicians = repository.getMusicians();
                val artists = bands + musicians
                val sortedArtists = artists.sortedBy { it.name }
                _state.update { currentState ->
                    currentState.copy(
                        artists = sortedArtists,
                    )
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}