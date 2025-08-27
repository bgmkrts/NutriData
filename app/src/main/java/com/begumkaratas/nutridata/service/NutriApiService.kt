package com.begumkaratas.nutridata.service

import com.begumkaratas.nutridata.model.Nutri
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NutriApiService {
    private val api= Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NutriAPI::class.java)

   suspend fun getData():List<Nutri>{
        return api.getNutri()
    }

}

