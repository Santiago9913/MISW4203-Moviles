package com.grupo3.vinilos.album.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.vinilos.album.service.AlbumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

class AlbumsViewModel : ViewModel() {
    private val repository = AlbumRepository()

    private val _state = MutableStateFlow(AlbumsListState())
    val state: StateFlow<AlbumsListState> = _state.asStateFlow()

    fun getAlbums() {
        viewModelScope.launch {
            try {
                val albums = repository.getAlbums()

                _state.update { currentState ->
                    currentState.copy(
                        albums = albums,
                    )
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}