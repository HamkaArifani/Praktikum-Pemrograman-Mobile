package com.example.filafixml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filafixml.data.DataSource
import com.example.filafixml.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class FilmViewModel(
    private val dataSource : DataSource,
    val pageInfo : String
): ViewModel() {
    private val _movieList = MutableStateFlow<List<Film>>(emptyList())
    private val _highlightList = MutableStateFlow<List<Film>>(emptyList())

    val movieList: StateFlow<List<Film>> = _movieList.asStateFlow()
    val highlightList: StateFlow<List<Film>> = _highlightList.asStateFlow()

    init {
        loadData()
    }

    fun loadData(){
        val allFilm = dataSource.listFilm()

        _movieList.value = allFilm
        Timber.d("Data Item yang Masuk Pada List : ${allFilm.size}")
        _highlightList.value = allFilm
    }
}