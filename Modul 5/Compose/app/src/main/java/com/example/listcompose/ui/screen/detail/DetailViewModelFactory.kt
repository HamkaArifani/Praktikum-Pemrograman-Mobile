package com.example.listcompose.ui.screen.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listcompose.data.local.pref.SettingPreferences
import com.example.listcompose.data.local.room.FilmDatabase
import com.example.listcompose.data.remote.api.NetworkModule
import com.example.listcompose.data.repository.FilmRepository

class DetailViewModelFactory(
    private val context : Context,
    private val filmId : Int,
    private val pageInfo : String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            val apiService = NetworkModule.tmdbService
            val database = FilmDatabase.getDatabase(context)
            val filmDao = database.filmDao()
            val settingPreferences = SettingPreferences(context)
            val repository = FilmRepository(apiService, filmDao, settingPreferences)

            return DetailViewModel(repository, filmId, pageInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
    }
}