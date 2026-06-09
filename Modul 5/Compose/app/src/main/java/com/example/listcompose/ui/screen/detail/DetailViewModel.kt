package com.example.listcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listcompose.data.repository.FilmRepository
import com.example.listcompose.domain.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    private val repository: FilmRepository,
    val  filmId: Int,
    val pageInfo: String
): ViewModel() {
    val film: StateFlow<Film?> = repository.getMovieById(filmId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}