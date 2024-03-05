package com.tegar.sedekah.core.data.remote

import android.util.Log
import com.tegar.sedekah.core.data.remote.network.ApiResponse
import com.tegar.sedekah.core.data.remote.network.ApiService
import com.tegar.sedekah.core.data.remote.response.CampaignResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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
}