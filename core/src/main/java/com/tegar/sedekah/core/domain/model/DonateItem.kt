package com.tegar.sedekah.core.domain.model

data class DonateItem (
    val date : String ,
    val amount : String,
    val user : User,
    val campaign : Campaign
)