package com.tegar.sedekah.core.domain.model

data class AuthResult (
    val error : Boolean,
    val message: String,
    val result : User?
)

