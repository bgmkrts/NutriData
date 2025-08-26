package com.begumkaratas.nutridata.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.begumkaratas.nutridata.model.Nutri

@Database(entities = [Nutri::class], version = 1)
abstract class NutriDatabase : RoomDatabase() {
    abstract fun nutriDAO(): NutriDAO
}