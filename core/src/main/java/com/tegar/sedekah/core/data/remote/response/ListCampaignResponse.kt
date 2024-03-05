package com.tegar.sedekah.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListCampaignResponse(

	@field:SerializedName("data")
	val data: List<CampaignResponse>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

