package com.tegar.sedekah.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "campaigns") // Tentukan nama tabel database
data class CampaignEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "judul")
    val judul: String,

    @ColumnInfo(name = "deskripsi")
    val deskripsi: String,

    @ColumnInfo(name = "cerita")
    val cerita: String,

    @ColumnInfo(name = "tanggal_mulai")
    val tanggalMulai: String,

    @ColumnInfo(name = "tanggal_selesai")
    val tanggalSelesai: String,

    @ColumnInfo(name = "foto")
    val foto: String,

    @ColumnInfo(name = "dana_terkumpul")
    val danaTerkumpul: Long,

    @ColumnInfo(name = "target_donasi")
    val targetDonasi: Long,

    @ColumnInfo(name = "kategori")
    val kategori: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = null


)
