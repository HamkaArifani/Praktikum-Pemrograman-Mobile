package com.example.listcompose.ui.screen.language

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listcompose.data.local.pref.SettingPreferences

class LanguageViewModelFactory(
    private val context: Context,
    private val pageInfo : String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LanguageViewModel::class.java)){
            val settingPreferences = SettingPreferences(context)
            return LanguageViewModel(settingPreferences, pageInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
    }
}