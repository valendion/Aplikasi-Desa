package com.example.desaa.adapter.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.databinding.LayoutSocialAssistanceListBinding
import com.example.desaa.model.DummyData

class AdapterSocialAssistanceJamkes :
    RecyclerView.Adapter<AdapterSocialAssistanceJamkes.SocialAssitanceJamkes>() {
    private var _listSocialAssistance = arrayListOf<DummyData>()

    inner class SocialAssitanceJamkes(val binding: LayoutSocialAssistanceListBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialAssitanceJamkes {
        val itemBinding =
            LayoutSocialAssistanceListBinding.inflate(LayoutInflater.from(parent.context))
        return SocialAssitanceJamkes(itemBinding)
    }

    override fun onBindViewHolder(holder: SocialAssitanceJamkes, position: Int) {
        holder.bind(_listSocialAssistance[position])
    }

    override fun getItemCount(): Int = _listSocialAssistance.size

}