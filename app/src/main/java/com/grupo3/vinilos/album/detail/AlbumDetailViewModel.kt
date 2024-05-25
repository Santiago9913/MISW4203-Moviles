package com.grupo3.vinilos.album.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.vinilos.album.dto.SongCreateDto
import com.grupo3.vinilos.album.service.AlbumRepository
import com.grupo3.vinilos.utils.ERROR_MESSAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumDetailViewModel : ViewModel() {
    private val repository = AlbumRepository()

    private val _state = MutableStateFlow(AlbumDetailState())
    val state: StateFlow<AlbumDetailState> = _state.asStateFlow()

    fun getAlbumDetail(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val album = repository.getAlbumsById(albumId)
            _state.update { currentState ->
                currentState.copy(
                    album = album,
                )
            }
        }
    }

    fun getSongs(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val songs = repository.getSongs(albumId)
            _state.update { currentState ->
                currentState.copy(
                    songs = songs,
                )
            }
        }
    }

    fun addSong(albumId: Int, song: SongCreateDto) {
        viewModelScope.launch {
            try {
                val newSong = repository.addSong(albumId, song)
                _state.update { currentState ->
                    currentState.copy(
                        songs = currentState.songs + newSong,
                        errorMessage = null
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