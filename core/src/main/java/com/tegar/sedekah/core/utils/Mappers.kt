package com.tegar.sedekah.core.utils

import com.tegar.sedekah.core.data.local.entity.CampaignEntity
import com.tegar.sedekah.core.data.remote.response.CampaignResponse
import com.tegar.sedekah.core.data.remote.response.UserResponse
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.core.domain.model.User

// Convert Response to Entity
fun CampaignResponse.toEntity(): CampaignEntity {
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

    )
}

fun UserResponse.toDomain() : User{
    return User(
        name = name,
        id = id,
        email = email,
        token = token
    )
}


fun User.toModel() : UserResponse{
    return UserResponse(
        name = name,
        id = id,
        email = email,
        token = token ?: ""
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
