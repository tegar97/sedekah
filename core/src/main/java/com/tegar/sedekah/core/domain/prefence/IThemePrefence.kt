package com.tegar.sedekah.core.domain.prefence

import kotlinx.coroutines.flow.Flow

interface IThemePrefence {

    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)

}