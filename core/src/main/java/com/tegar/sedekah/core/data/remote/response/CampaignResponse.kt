package com.tegar.sedekah.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CampaignResponse(

    @field:SerializedName("cerita")
    val cerita: String,

    @field:SerializedName("tanggal_selesai")
    val tanggalSelesai: String,

    @field:SerializedName("foto")
    val foto: String,

    @field:SerializedName("tanggal_mulai")
    val tanggalMulai: String,

    @field:SerializedName("dana_terkumpul")
    val danaTerkumpul: Long,

    @field:SerializedName("kategori")
    val kategori: String,

    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("target_donasi")
    val targetDonasi: Long
)
