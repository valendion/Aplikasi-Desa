package com.example.desaa.adapter.headman

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.databinding.LayoutGovernmentOfficialsListBinding
import com.example.desaa.model.response.ModelDataVillageGovernmentOfficials

class AdapterVillageGovernmentOfficials :
    RecyclerView.Adapter<AdapterVillageGovernmentOfficials.VillageGovernmentOfficialViewHolder>() {
    private var _listSocialAssistance = arrayListOf<ModelDataVillageGovernmentOfficials>()

    inner class VillageGovernmentOfficialViewHolder(val binding: LayoutGovernmentOfficialsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDataVillageGovernmentOfficials) {
            binding.apply {
                textName.text = data.namaPegawaiDesa
                textNIK.text = data.nik
                textNIP.text = data.nip
                textPosition.text = data.jabatan
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<ModelDataVillageGovernmentOfficials>?) {
        list?.forEach { model ->
            if (model != null) {
                add(model)
            }
        }
    }

    private fun add(model: ModelDataVillageGovernmentOfficials) {
        _listSocialAssistance.add(model)
        notifyItemInserted(_listSocialAssistance.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VillageGovernmentOfficialViewHolder {
        val itemBinding =
            LayoutGovernmentOfficialsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VillageGovernmentOfficialViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AdapterVillageGovernmentOfficials.VillageGovernmentOfficialViewHolder, position: Int) {
        holder.bind(_listSocialAssistance[position])
    }

    override fun getItemCount(): Int = _listSocialAssistance.size

}