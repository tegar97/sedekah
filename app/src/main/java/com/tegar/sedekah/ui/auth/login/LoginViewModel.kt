package com.tegar.sedekah.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tegar.sedekah.core.domain.model.User
import com.tegar.sedekah.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    fun login(email: String, password: String) = authUseCase.login(email, password).asLiveData()
    fun saveSession(userModel: User) {
        viewModelScope.launch {
            authUseCase.saveSession(userModel)
        }
    }

    val getSession = authUseCase.getSession().asLiveData()


}
