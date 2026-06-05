package com.example.listcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.listcompose.data.DataSource
import com.example.listcompose.domain.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val dataSource: DataSource,
    val pageInfo : String
) : ViewModel() {
    private val _films = MutableStateFlow(dataSource.listFilm())
    val films: StateFlow<List<Film>> = _films.asStateFlow()
}