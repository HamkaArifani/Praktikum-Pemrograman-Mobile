package com.example.filafixml.viewmodel

import androidx.lifecycle.ViewModel
import com.example.filafixml.data.DataSource

class DetailViewModel (
    private val dataSource: DataSource,
    val filmId : Int,
    val pageInfo : String
): ViewModel(){
    val film = dataSource.listFilm().find { it.id== filmId }
}