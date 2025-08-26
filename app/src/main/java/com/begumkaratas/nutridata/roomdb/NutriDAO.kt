package com.begumkaratas.nutridata.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.begumkaratas.nutridata.model.Nutri

@Dao
interface NutriDAO {
    //vararg->aynı anda birden fazla istediğimiz sayıda besin eklemek istersek
    @Insert
    suspend fun insertAll(vararg nutri: Nutri):List<Long>
    //Eklediği besinlerin idsini long olarak geri veriyor.

    @Query("Delete from nutri")
    suspend fun getAll(): List<Nutri>

    @Query("SELECT * FROM nutri where uuid=:id")
    suspend fun getNutri(id: Int): Nutri

    @Query("Select * from nutri")
    suspend fun deleteAll()
}