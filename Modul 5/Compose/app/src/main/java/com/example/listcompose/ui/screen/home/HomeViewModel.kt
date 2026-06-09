package com.example.listcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listcompose.data.repository.FilmRepository
import com.example.listcompose.domain.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val repository: FilmRepository,
    val pageInfo : String
) : ViewModel() {
    val films: StateFlow<List<Film>> = repository.getAllMovies()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    init {
        fetchMovieFromServer()
    }
    private fun fetchMovieFromServer(){
        viewModelScope.launch {
            val errorResult = repository.refreshMovies()
            if (errorResult != null) {
                Timber.e("SINKRONISASI GAGAL! Faktor Penyebab: $errorResult")
            } else {
                Timber.d("SINKRONISASI SUKSES! Data film berhasil masuk ke Room.")
            }
        }
    }
}