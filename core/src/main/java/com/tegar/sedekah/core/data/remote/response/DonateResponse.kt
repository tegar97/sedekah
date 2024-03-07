package com.tegar.sedekah.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DonateResponse(

	@field:SerializedName("data")
	val data: DonateItemResponse ,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)


data class DonateItemResponse(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("amount")
	val amount: String,

	@field:SerializedName("campaign")
	val campaign: CampaignResponse,

	@field:SerializedName("user")
	val user: UserResponse,
)

