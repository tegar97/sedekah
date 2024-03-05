package com.tegar.sedekah.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Campaign (
    val id: Long,
    val judul: String,
    val deskripsi: String,
    val targetDonasi: Long,
    val tanggalMulai: String,
    val tanggalSelesai: String,
    val foto: String,
    val kategori: String,
    val danaTerkumpul: Long,
    val cerita: String,
    val isFavorite: Boolean

) : Parcelable
