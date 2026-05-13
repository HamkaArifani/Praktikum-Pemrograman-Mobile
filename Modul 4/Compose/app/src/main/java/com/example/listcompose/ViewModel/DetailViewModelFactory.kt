package com.example.listcompose.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listcompose.Data.DataSource

class DetailViewModelFactory(
    private val dataSource: DataSource,
    private val filmId : Int,
    private val pageInfo : String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(dataSource, filmId, pageInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}