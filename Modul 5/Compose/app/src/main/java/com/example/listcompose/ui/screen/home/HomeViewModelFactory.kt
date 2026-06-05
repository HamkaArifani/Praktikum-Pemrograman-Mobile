package com.example.listcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listcompose.data.DataSource

class HomeViewModelFactory(
    private val dataSource: DataSource,
    private val pageInfo : String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(dataSource, pageInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}