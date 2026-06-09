package com.example.listcompose.ui.screen.language

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listcompose.data.local.pref.SettingPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val settingPreferences: SettingPreferences,
    val pageInfo: String
) : ViewModel() {
    private val _selectedOption = MutableStateFlow("English")
    val selectedOption: StateFlow<String> = _selectedOption.asStateFlow()

    init {
        viewModelScope.launch {
            val currentLangCode = settingPreferences.getLanguageSetting.first()
            _selectedOption.value = if (currentLangCode == "in") "Bahasa" else "English"
        }
    }

    fun selectLanguage(language: String){
        _selectedOption.value = language
        val langCode = if (language == "Bahasa") "in" else "en"

        viewModelScope.launch {
            settingPreferences.saveLanguageSetting(langCode)
            val appLocale = LocaleListCompat.forLanguageTags(langCode)
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }
}