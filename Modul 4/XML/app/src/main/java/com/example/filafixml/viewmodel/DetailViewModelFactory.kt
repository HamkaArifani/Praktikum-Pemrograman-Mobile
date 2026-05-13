package com.example.filafixml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filafixml.data.DataSource

class DetailViewModelFactory(
    private val dataSource: DataSource,
    private val filmId: Int,
    private val pageInfo: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(dataSource, filmId, pageInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}