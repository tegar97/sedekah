package com.tegar.sedekah.core.data.remote.network

import com.tegar.sedekah.core.data.remote.response.DonateResponse
import com.tegar.sedekah.core.data.remote.response.ListCampaignResponse
import com.tegar.sedekah.core.data.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("all-campaign")
    suspend fun getCampaignList(): ListCampaignResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse


    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("nama") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse


    @FormUrlEncoded
    @POST("donate")
    suspend fun donate(
        @Field("id_campaign") idCampaign: Int,
        @Field("jumlah") amount: Int,

        ): DonateResponse

}
