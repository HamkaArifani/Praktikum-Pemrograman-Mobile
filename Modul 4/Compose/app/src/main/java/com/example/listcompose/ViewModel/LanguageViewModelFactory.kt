package com.example.listcompose.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LanguageViewModelFactory(
    private val pageInfo : String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LanguageViewModel::class.java)){
            return LanguageViewModel(pageInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}