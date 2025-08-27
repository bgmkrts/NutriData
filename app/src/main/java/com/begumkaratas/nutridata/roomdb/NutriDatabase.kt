package com.begumkaratas.nutridata.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.begumkaratas.nutridata.model.Nutri

@Database(entities = [Nutri::class], version = 1)
abstract class NutriDatabase : RoomDatabase() {
    abstract fun nutriDAO(): NutriDAO


    //Singleton
//farklı threadlerden aynı anda bir veriyi değiştirmeye çalışırsak aynı anda yazmayı okumayı vs. engelleyecek
    companion object {

        @Volatile
        private var instance: NutriDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

//roomdatabase
        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NutriDatabase::class.java, "nutridatabase"
        ).build()

    }
}