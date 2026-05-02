package com.example.listcompose.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.listcompose.Data.DataSource
import com.example.listcompose.Model.Film
import androidx.compose.runtime.State

class FilmViewModel : ViewModel() {
    private val dataSource = DataSource()
    private val _films = mutableStateOf(dataSource.listFilm())
    val films: State<List<Film>> = _films

    fun getFilmById(filmId: Int): Film? {
        return _films.value.find { it.id == filmId }
    }
}