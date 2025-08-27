package com.begumkaratas.nutridata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.begumkaratas.nutridata.model.Nutri
import com.begumkaratas.nutridata.roomdb.NutriDatabase
import kotlinx.coroutines.launch

class NutriDetailViewModel(application: Application):AndroidViewModel(application) {
    val nutriLiveData=MutableLiveData<Nutri>()
    fun getRoomData(uuid:Int){
        viewModelScope.launch {
            val dao=NutriDatabase(getApplication()).nutriDAO()
            val nutri=dao.getNutri(uuid)
            nutriLiveData.value=nutri
        }
    }

}