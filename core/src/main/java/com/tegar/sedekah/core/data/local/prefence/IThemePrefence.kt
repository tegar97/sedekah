package com.tegar.sedekah.core.data.local.prefence

import kotlinx.coroutines.flow.Flow

interface IThemePrefence {

    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)

}