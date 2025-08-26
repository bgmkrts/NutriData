package com.begumkaratas.nutridata.model

import com.google.gson.annotations.SerializedName

data class Nutri(
    @SerializedName("isim")
    val besinIsim: String?,

    val kalori: String?,
    val karbonhidrat: String?,
    val protein: String?,
    val yag: String?,
    val gorsel: String?
)
