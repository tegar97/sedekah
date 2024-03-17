package com.tegar.sedekah.core.domain.usecase


import com.tegar.sedekah.core.domain.model.User
import com.tegar.sedekah.core.domain.repository.IAuthRepository


class AuthInteractor(private val authRepository: IAuthRepository ) : AuthUseCase {
    override fun login(email: String, password: String) = authRepository.login(email, password)
    override fun register(name: String , email: String, password: String) = authRepository.register(name,email, password)

    override suspend fun saveSession(userModel : User) = authRepository.saveSession(userModel)
    override  fun getSession() = authRepository.getSession()
    override suspend fun clearSession()  = authRepository.clearSession()

}