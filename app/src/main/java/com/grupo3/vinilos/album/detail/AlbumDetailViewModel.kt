package com.grupo3.vinilos.album.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo3.vinilos.album.service.AlbumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumDetailViewModel : ViewModel() {
    private val repository = AlbumRepository()

    private val _state = MutableStateFlow(AlbumDetailState())
    val state: StateFlow<AlbumDetailState> = _state.asStateFlow()

    fun getAlbumDetail(albumId: Int){
        viewModelScope.launch {
            val album = repository.getAlbumsById(albumId)
            _state.update { currentState ->
                currentState.copy(
                    album = album,
                )
            }
        }
    }

    fun getSongs() {
        viewModelScope.launch {
            val songs = repository.getSongs(state.value?.albumId)
        }
    }
}