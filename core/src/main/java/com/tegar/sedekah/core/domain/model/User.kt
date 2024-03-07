package com.tegar.sedekah.core.domain.model

data class   User (
    val name: String,
    val id: Int,
    val email: String,
    val token: String? = null ,
)