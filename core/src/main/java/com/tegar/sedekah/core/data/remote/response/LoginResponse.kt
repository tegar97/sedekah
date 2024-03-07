package com.tegar.sedekah.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("data")
    val data: UserResponse,

    @field:SerializedName("success")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)


data class UserResponse(

    @field:SerializedName("nama")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("token")
    val token: String,


    @field:SerializedName("email")
    val email: String
)