package com.example.listcompose.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "filafi_seetting")
class SettingPreferences(private val context: Context){
    private val LANGUAGE_KEY =  stringPreferencesKey("app_language")

    val getLanguageSetting: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[LANGUAGE_KEY]?: "en-US" }
    suspend fun saveLanguageSetting(languageCode: String){
        context.dataStore.edit { preferences -> preferences[LANGUAGE_KEY]= languageCode }
    }
}