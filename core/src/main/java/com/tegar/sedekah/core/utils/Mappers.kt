package com.tegar.sedekah.core.utils

import com.tegar.sedekah.core.data.local.entity.CampaignEntity
import com.tegar.sedekah.core.data.remote.response.CampaignResponse
import com.tegar.sedekah.core.domain.model.Campaign

// Convert Response to Entity
fun CampaignResponse.toEntity(): CampaignEntity {
    return CampaignEntity(
        id = id,
        judul = judul, // Assuming 'judul' maps to 'title'
        deskripsi = deskripsi,
        cerita = cerita,
        tanggalMulai = tanggalMulai,
        tanggalSelesai = tanggalSelesai,
        foto = foto,
        danaTerkumpul = danaTerkumpul,
        targetDonasi = targetDonasi,
        kategori = kategori,

    )
}

// Convert Entity to Domain
fun CampaignEntity.toDomain(): Campaign {
    return Campaign(
        id = id,
        judul = judul,
        deskripsi = deskripsi,
        cerita = cerita,
        tanggalMulai = tanggalMulai,
        tanggalSelesai = tanggalSelesai,
        foto = foto,
        danaTerkumpul = danaTerkumpul,
        targetDonasi = targetDonasi,
        kategori = kategori,
        isFavorite = isFavorite ?: false
    )
}

// Convert Domain to Entity
fun Campaign.toEntity(): CampaignEntity {
    return CampaignEntity(
        id = id,
        judul = judul,
        deskripsi = deskripsi,
        cerita = cerita,
        tanggalMulai = tanggalMulai,
        tanggalSelesai = tanggalSelesai,
        foto = foto,
        danaTerkumpul = danaTerkumpul,
        targetDonasi = targetDonasi,
        kategori = kategori,
        isFavorite = isFavorite
    )
}
