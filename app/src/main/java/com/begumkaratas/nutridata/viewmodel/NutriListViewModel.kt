package com.begumkaratas.nutridata.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.begumkaratas.nutridata.model.Nutri
import com.begumkaratas.nutridata.roomdb.NutriDAO
import com.begumkaratas.nutridata.roomdb.NutriDatabase
import com.begumkaratas.nutridata.service.NutriApiService
import com.begumkaratas.nutridata.util.SpecificSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NutriListViewModel(application: Application) : AndroidViewModel(application) {

    val nutries = MutableLiveData<List<Nutri>>()  // besinler
    val nutriErrorMessage = MutableLiveData<Boolean>() //hata mesajı
    val nutriLoading = MutableLiveData<Boolean>() //progressbar


    private val nutriApiService = NutriApiService()
    private val specificSharedPreferences = SpecificSharedPreferences(getApplication())
    private val updateTime = 0.1 * 60 * 1000 * 1000 * 1000L

     fun refreshData() {
        val saveTime = specificSharedPreferences.zamaniAl()
        if (saveTime != null && saveTime != 0L && System.nanoTime() - saveTime < updateTime) {
            //roomdan verileri alacağız
            getDataRoom()
        } else {
            getDataInternet()
        }
    }

    fun refreshDataFromInternet(){
        getDataInternet()
    }

    private fun getDataRoom() {
        nutriLoading.value=true
        viewModelScope.launch(Dispatchers.IO) {
            val nutriList=NutriDatabase(getApplication()).nutriDAO().getAll()
            withContext(Dispatchers.Main){
                showNutri(nutriList)
                Toast.makeText(getApplication(), "Besinleri roomdan aldık", Toast.LENGTH_LONG)
                    .show()

            }
        }

    }

    private fun getDataInternet() {
        nutriLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val nutriList = nutriApiService.getData()

            withContext(Dispatchers.Main) {
                nutriLoading.value = false
                // nutries.value=nutriList
                //room /sqllite kaydedilecek
                roomSave(nutriList)

                Toast.makeText(getApplication(), "Besinleri internetten aldık", Toast.LENGTH_LONG)
                    .show()

            }


        }

    }

    private fun showNutri(nutriList: List<Nutri>) {
        nutries.value = nutriList
        nutriErrorMessage.value = false
        nutriLoading.value = false


    }

    private fun roomSave(nutriList: List<Nutri>) {
        viewModelScope.launch {
            val dao = NutriDatabase(getApplication()).nutriDAO()
            dao.deleteAll()
            //* → bir array’i vararg parametreye açmak için kullanılır.“array’i tek tek parametre olarak aç” demek.
            val uuidList = dao.insertAll(*nutriList.toTypedArray())
            var i = 0
            while (i < nutriList.size) {
                nutriList[i].uuid = uuidList[i].toInt()
                i = i + 1

            }
            showNutri(nutriList)
        }
        specificSharedPreferences.zamaniKaydet(System.nanoTime())

    }
}