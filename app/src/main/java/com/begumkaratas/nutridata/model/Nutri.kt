package com.begumkaratas.nutridata.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Nutri(
    @ColumnInfo("name=isim")
    @SerializedName("isim")
    val besinIsim: String?,
    @ColumnInfo("name=kalori")

    val kalori: String?,
    @ColumnInfo("name=karbonhidrat")

    val karbonhidrat: String?,
    @ColumnInfo("name=protein")

    val protein: String?,
    @ColumnInfo("name=yag")

    val yag: String?,
    @ColumnInfo("name=gorsel")

    val gorsel: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}
