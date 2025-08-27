package com.begumkaratas.nutridata.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SpecificSharedPreferences {
//en son verinin ne zaman çekildiğini tutar
    companion object {

        private const val TIME = "time"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: SpecificSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context): SpecificSharedPreferences =
            instance ?: synchronized(lock) {
                instance ?: makeSharedPreferences(context).also {
                    instance = it
                }
            }

        private fun makeSharedPreferences(context: Context): SpecificSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return SpecificSharedPreferences()
        }

        fun zamaniKaydet(zaman: Long) {
            sharedPreferences?.edit()?.putLong(TIME, zaman)?.apply()
        }

        fun zamaniAl(): Long {
            return sharedPreferences?.getLong(TIME, 0) ?: 0
        }
    }
}
