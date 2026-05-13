package com.example.filafixml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filafixml.data.DataSource

class FilmViewModelFactory(
    private val dataSource: DataSource,
    private val pageInfo : String
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmViewModel::class.java)){
            return FilmViewModel(dataSource, pageInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}