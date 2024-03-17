package com.tegar.sedekah.core.domain.usecase

import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    fun login(email: String, password: String): Flow<Resource<User>>
    fun register(name : String , email: String, password: String): Flow<Resource<User>>

    suspend fun saveSession(userModel: User)
    fun getSession() : Flow<User>
    suspend fun clearSession()


}