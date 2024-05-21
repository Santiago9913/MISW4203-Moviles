package com.grupo3.vinilos.album.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.AlbumRegistrationDto
import com.grupo3.vinilos.album.service.AlbumRepository
import com.grupo3.vinilos.utils.ERROR_MESSAGE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AlbumsViewModel : ViewModel() {
    private val repository = AlbumRepository()

    private val _state = MutableStateFlow(AlbumsListState())
    val state: StateFlow<AlbumsListState> = _state.asStateFlow()

    suspend fun getAlbums() {
        try {
            val albums = repository.getAlbums()

            _state.update { currentState ->
                currentState.copy(
                    albums = albums,
                    errorMessage = null,
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

    suspend fun createAlbum(album: AlbumRegistrationDto) {
        try {
            repository.createAlbum(album = album);
        } catch (e: Exception) {
            _state.update { currentState ->
                currentState.copy(
                    errorMessage = ERROR_MESSAGE
                )

            }
        }
    }
}