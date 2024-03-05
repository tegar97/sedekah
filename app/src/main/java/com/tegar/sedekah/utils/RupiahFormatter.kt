package com.tegar.sedekah.utils

import java.text.NumberFormat
import java.util.Locale

fun Long.toRupiah(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    return formatter.format(this)
}