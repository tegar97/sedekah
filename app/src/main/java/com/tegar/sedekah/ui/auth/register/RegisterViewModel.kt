package com.tegar.sedekah.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tegar.sedekah.core.domain.usecase.AuthUseCase

class RegisterViewModel(private val authUseCase: AuthUseCase) : ViewModel()  {

    fun register(name : String , email: String, password: String) = authUseCase.register(name,email, password).asLiveData()

}