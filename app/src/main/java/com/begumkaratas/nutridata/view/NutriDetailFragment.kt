package com.begumkaratas.nutridata.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.begumkaratas.nutridata.databinding.FragmentNutriDetailBinding
import com.begumkaratas.nutridata.databinding.FragmentNutriListBinding
import com.begumkaratas.nutridata.util.fotoDownload
import com.begumkaratas.nutridata.util.placeholderYap
import com.begumkaratas.nutridata.viewmodel.NutriDetailViewModel


class NutriDetailFragment : Fragment() {



    private var _binding: FragmentNutriDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel:NutriDetailViewModel
    var nutriId=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutriDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this)[NutriDetailViewModel::class.java]
        arguments?.let {
            nutriId=NutriDetailFragmentArgs.fromBundle(it).nutriId


        }
        viewModel.getRoomData(nutriId)
        observeLiveData()
    }
private fun observeLiveData(){
    viewModel.nutriLiveData.observe(viewLifecycleOwner){
        binding.nutriName.text=it.besinIsim
        binding.nutriCalory.text=it.kalori
        binding.nutriProtein.text=it.protein
        binding.nutricarbohydrat.text=it.karbonhidrat
        binding.nutriOil.text=it.yag
        binding.imageView2.fotoDownload(it.gorsel, placeholderYap(requireContext()))
    }
}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}