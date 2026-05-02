package com.example.filafixml.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filafixml.Data.DataSource
import com.example.filafixml.Model.Film

class FilmViewModel: ViewModel() {
    private val dataSource = DataSource()

    private val _movieList = MutableLiveData<List<Film>>()
    private val _highlightList = MutableLiveData<List<Film>>()

    val movieList: LiveData<List<Film>> = _movieList
    val highlightList: LiveData<List<Film>> = _highlightList

    init {
        loadData()
    }

    fun loadData(){
        val allFilm = dataSource.listFilm()

        _movieList.value = allFilm
        _highlightList.value = allFilm
    }
}