package com.begumkaratas.nutridata.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.begumkaratas.nutridata.databinding.FragmentNutriListBinding
import com.begumkaratas.nutridata.service.NutriAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NutriListFragment : Fragment() {
    private var _binding: FragmentNutriListBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutriListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swiperefreshlayout.setOnRefreshListener {

        }
    //--------------retrofit kullanımı----------------------
        /*val retrofit=Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NutriAPI::class.java)

        //coroutine->Eşzamanlı bir eş zamanlılık tasarım kalıbıdır
        //Kullanıcı arabiriminin bloklanmadan ya da donmadan çalışmasını,
        // ağır görevlerin arka planda gerçekleşmesini sağlayabilmekteyiz


        CoroutineScope(Dispatchers.IO).launch {
            val nutries=retrofit.getNutri()
            nutries.forEach(){
                println(it.besinIsim)
            }
        } */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}