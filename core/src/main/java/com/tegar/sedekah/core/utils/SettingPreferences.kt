package com.tegar.sedekah.core.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.tegar.sedekah.core.domain.prefence.IThemePrefence
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
private val readOnlyProperty = preferencesDataStore( "access")

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

class SettingPreferences(private val dataStore: DataStore<Preferences>) : IThemePrefence  {

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    override fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }



}