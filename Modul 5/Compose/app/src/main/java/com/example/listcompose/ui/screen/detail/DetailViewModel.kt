package com.example.listcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.example.listcompose.data.DataSource
import com.example.listcompose.domain.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(
    private val dataSource: DataSource,
    val  filmId: Int,
    val pageInfo: String
): ViewModel() {
    private val _films = MutableStateFlow(dataSource.listFilm())
    val films: StateFlow<List<Film>> = _films.asStateFlow()
    fun getFilmById(filmId: Int): Film? {
        return _films.value.find { it.id == filmId }
    }
}