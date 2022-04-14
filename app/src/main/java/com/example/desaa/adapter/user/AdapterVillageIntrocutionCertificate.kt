package com.example.desaa.adapter.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.databinding.LayoutConfirmAcceptListBinding
import com.example.desaa.model.response.DummyData

class AdapterVillageIntrocutionCertificate :
    RecyclerView.Adapter<AdapterVillageIntrocutionCertificate.VillageIntroductionCertificate>() {
    private var _listSocialAssistance = arrayListOf<DummyData>()

    inner class VillageIntroductionCertificate(val binding: LayoutConfirmAcceptListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DummyData) {
            binding.apply {
                textTitle.text = data.name
                textValue.text = data.nik
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<DummyData>) {
        list.forEach { model ->
            if (model != null) {
                add(model)
            }
        }
    }

    private fun add(model: DummyData) {
        _listSocialAssistance.add(model)
        notifyItemInserted(_listSocialAssistance.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VillageIntroductionCertificate {
        val itemBinding =
            LayoutConfirmAcceptListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VillageIntroductionCertificate(itemBinding)
    }

    override fun onBindViewHolder(holder: VillageIntroductionCertificate, position: Int) {
        holder.bind(_listSocialAssistance[position])
    }

    override fun getItemCount(): Int = _listSocialAssistance.size

}