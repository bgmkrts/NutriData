package com.begumkaratas.nutridata.service

import com.begumkaratas.nutridata.model.Nutri
import retrofit2.http.GET

interface NutriAPI {
    //

    //BASE URL->https://raw.githubusercontent.com/
    //ENDPOINT->https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
   suspend fun getNutri():List<Nutri>

}