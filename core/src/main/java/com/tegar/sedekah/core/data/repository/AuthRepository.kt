package com.tegar.sedekah.core.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.core.data.remote.RemoteDataSource
import com.tegar.sedekah.core.data.remote.network.ApiResponse
import com.tegar.sedekah.core.domain.model.User
import com.tegar.sedekah.core.domain.repository.IAuthRepository
import com.tegar.sedekah.core.utils.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepository(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStore<Preferences>

) : IAuthRepository {


    override fun login(email: String, password: String): Flow<Resource<User>> {
        return remoteDataSource.login(email, password)
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val user = apiResponse.data.toDomain()
                        Resource.Success(user)
                    }

                    is ApiResponse.Error -> {
                        Resource.Error(apiResponse.errorMessage)
                    }

                    else -> {
                        Resource.Loading()
                    }
                }
            }
    }

    override fun register(name: String, email: String, password: String): Flow<Resource<User>> {
        return remoteDataSource.register(name,email, password)
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val user = apiResponse.data.toDomain()
                        Resource.Success(user)
                    }

                    is ApiResponse.Error -> {
                        Resource.Error(apiResponse.errorMessage)
                    }

                    else -> {
                        Resource.Loading()
                    }
                }
            }
    }

    override suspend fun saveSession(userModel: User) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = userModel.id
            preferences[TOKEN] = userModel.token ?: ""
            preferences[NAME] = userModel.name
            preferences[EMAIL] = userModel.email
        }
        Log.d("save_to_prefence", "true")
    }

    override fun getSession(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                preferences[NAME] ?: "",
                preferences[USER_ID] ?: 0,
                preferences[EMAIL] ?: "",
                preferences[TOKEN] ?: ""
            )
        }
    }

    override suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.remove(EMAIL)
            preferences.remove(NAME)
            preferences.remove(USER_ID)
            preferences.remove(TOKEN)

        }
    }


    private companion object {
        private val EMAIL = stringPreferencesKey("EMAIL")
        private val NAME = stringPreferencesKey("NAME")

        private val USER_ID = intPreferencesKey("USER_ID")
        private val TOKEN = stringPreferencesKey("TOKEN")

    }

}