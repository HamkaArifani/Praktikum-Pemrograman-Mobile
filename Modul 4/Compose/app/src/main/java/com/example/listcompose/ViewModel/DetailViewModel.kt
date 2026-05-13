package com.example.listcompose.ViewModel

import androidx.lifecycle.ViewModel
import com.example.listcompose.Data.DataSource
import com.example.listcompose.Model.Film
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