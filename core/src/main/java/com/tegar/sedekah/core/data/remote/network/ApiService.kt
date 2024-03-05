package com.tegar.sedekah.core.data.remote.network

import com.tegar.sedekah.core.data.remote.response.ListCampaignResponse
import retrofit2.http.GET

interface ApiService {
    @GET("all-campaign")
    suspend fun getCampaignList(): ListCampaignResponse
}
