package com.tegar.sedekah.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tegar.sedekah.core.domain.usecase.AuthUseCase
import com.tegar.sedekah.core.data.local.prefence.SettingPreferences
import kotlinx.coroutines.launch

class ProfileViewModel(private val pref: SettingPreferences, private val  authUseCase: AuthUseCase) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }

     fun  clearSesion() {
         viewModelScope.launch {
             authUseCase.clearSession()
         }
     }
}