package com.grupo3.vinilos.album.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.vinilos.album.dto.AlbumRegistrationDto
import com.grupo3.vinilos.album.service.AlbumRepository
import com.grupo3.vinilos.utils.ALBUM_REGISTRADO_EXITOSAMENTE
import com.grupo3.vinilos.utils.ERROR_MESSAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AlbumsRegistrationViewModel : ViewModel() {
    private val repository = AlbumRepository()

    private val _state = MutableStateFlow(AlbumsRegistrationState())
    val state: StateFlow<AlbumsRegistrationState> = _state.asStateFlow()

    fun createAlbum(album: AlbumRegistrationDto) {
        viewModelScope.launch()  {
            withContext(Dispatchers.IO) {
                try {
                    _state.update { currentState -> currentState.copy(loading = true) }
                    repository.createAlbum(album = album);
                    _state.update { currentState -> currentState.copy(succeedMessage = ALBUM_REGISTRADO_EXITOSAMENTE, loading = false) }
                } catch (e: Exception) {
                    _state.update { currentState ->
                        currentState.copy(
                            errorMessage = ERROR_MESSAGE,
                            loading = false,
                        )
                    }
                }
            }
        }
    }

    fun isFormValid(name:String, cover:String, genre:String, recordLabel: String, releaseDate: String, description:String):Boolean{
        return repository.isAlbumValid(name,cover,genre, recordLabel, releaseDate, description)
    }
}