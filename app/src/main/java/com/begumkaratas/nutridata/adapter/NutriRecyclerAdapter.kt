package com.begumkaratas.nutridata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.begumkaratas.nutridata.databinding.NutriRecyclerRowBinding
import com.begumkaratas.nutridata.model.Nutri
import com.begumkaratas.nutridata.view.NutriListFragmentDirections
import com.begumkaratas.nutridata.adapter.NutriRecyclerAdapter.nutriViewHolder as nutriViewHolder1

class NutriRecyclerAdapter(val nutriList:ArrayList<Nutri>):RecyclerView.Adapter<NutriRecyclerAdapter.nutriViewHolder> (){

    class nutriViewHolder(val binding:NutriRecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nutriViewHolder {
        val binding=NutriRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return nutriViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return nutriList.size
    }
    fun nutriListUpdate(newNutriList: List<Nutri>){
        nutriList.clear()
        nutriList.addAll(newNutriList)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: nutriViewHolder, position: Int) {
        holder.binding.name.text=nutriList[position].besinIsim
        holder.binding.calory.text=nutriList[position].kalori

        holder.itemView.setOnClickListener{
            val action=NutriListFragmentDirections.actionNutriListFragmentToNutriDetailFragment(nutriList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }
}