package com.begumkaratas.nutridata.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.begumkaratas.nutridata.adapter.NutriRecyclerAdapter
import com.begumkaratas.nutridata.databinding.FragmentNutriListBinding
import com.begumkaratas.nutridata.service.NutriAPI
import com.begumkaratas.nutridata.viewmodel.NutriListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NutriListFragment : Fragment() {
    private var _binding: FragmentNutriListBinding? = null
    private val binding get() = _binding!!

    private val nutriRecyclerAdapter=NutriRecyclerAdapter(arrayListOf())

    private lateinit var viewModel:NutriListViewModel
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
        viewModel=ViewModelProvider(this)[NutriListViewModel::class.java]
        viewModel.refreshData()
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=nutriRecyclerAdapter
        binding.swiperefreshlayout.setOnRefreshListener {
            binding.recyclerView.visibility=View.GONE
            binding.nutriErrorMessage.visibility=View.GONE
            binding.nutriLoading.visibility=View.VISIBLE

            viewModel.refreshDataFromInternet()
            binding.swiperefreshlayout.isRefreshing=false

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
        observeLiveData()
    }
    private fun observeLiveData(){

        viewModel.nutries.observe(viewLifecycleOwner){
            nutriRecyclerAdapter.nutriListUpdate(it)
            binding.recyclerView.visibility=View.VISIBLE
        }
        viewModel.nutriErrorMessage.observe(viewLifecycleOwner){
            if(it){
                binding.nutriErrorMessage.visibility=View.VISIBLE
                binding.recyclerView.visibility=View.GONE
            }else{
                binding.nutriErrorMessage.visibility=View.GONE

            }
        }
        viewModel.nutriLoading.observe(viewLifecycleOwner){
            if(it){
                binding.nutriErrorMessage.visibility=View.GONE
                binding.recyclerView.visibility=View.GONE
                binding.nutriLoading.visibility=View.VISIBLE

            }else{
                binding.nutriLoading.visibility=View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}