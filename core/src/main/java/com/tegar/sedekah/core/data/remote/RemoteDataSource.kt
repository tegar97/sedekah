package com.tegar.sedekah.core.data.remote

import android.util.Log
import com.google.gson.Gson
import com.tegar.sedekah.core.data.remote.network.ApiResponse
import com.tegar.sedekah.core.data.remote.network.ApiService
import com.tegar.sedekah.core.data.remote.response.CampaignResponse
import com.tegar.sedekah.core.data.remote.response.LoginResponse
import com.tegar.sedekah.core.data.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllCampaign(): Flow<ApiResponse<List<CampaignResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getCampaignList()
                val dataArray = response.data
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun login(email: String, password: String): Flow<ApiResponse<UserResponse>> =
        flow {
            try {
                val client = apiService.login(email, password).data
                emit(ApiResponse.Success(client))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
                val errorMessage = errorBody.message

                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(Dispatchers.IO)

    fun register(name : String , email: String, password: String): Flow<ApiResponse<UserResponse>> =
        flow {
            try {
                val client = apiService.register(name,email, password).data
                emit(ApiResponse.Success(client))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
                val errorMessage = errorBody.message

                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(Dispatchers.IO)


}