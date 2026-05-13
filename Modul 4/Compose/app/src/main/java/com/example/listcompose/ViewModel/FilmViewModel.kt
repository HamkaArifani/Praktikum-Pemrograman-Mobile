package com.example.listcompose.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.listcompose.Data.DataSource
import com.example.listcompose.Model.Film
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FilmViewModel(
    private val dataSource: DataSource,
    val pageInfo : String
) : ViewModel() {
    private val _films = MutableStateFlow(dataSource.listFilm())
    val films: StateFlow<List<Film>> = _films.asStateFlow()
}