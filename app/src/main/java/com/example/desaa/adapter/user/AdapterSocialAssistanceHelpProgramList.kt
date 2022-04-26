package com.example.desaa.adapter.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.R
import com.example.desaa.databinding.LayoutConfirmAcceptListBinding
import com.example.desaa.databinding.LayoutConfirmCheckListBinding
import com.example.desaa.databinding.LayoutSocialAssistanceListBinding
import com.example.desaa.model.response.ModelDataHelpProgramParticipant


class AdapterSocialAssistanceHelpProgramList :
    RecyclerView.Adapter<AdapterSocialAssistanceHelpProgramList.HelpProgramViewHolder>() {
    private var _listSocialAssistance = arrayListOf<ModelDataHelpProgramParticipant>()

    inner class HelpProgramViewHolder(val binding: LayoutConfirmAcceptListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDataHelpProgramParticipant) {
            binding.apply {
                textTitle.text = data.namaLengkap
                textValue.text = data.nik
                textNotif.text = itemView.context.getString(R.string.aktif)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<ModelDataHelpProgramParticipant>) {
        _listSocialAssistance.clear()
        list.forEach { model ->
            if (model != null) {
                add(model)
            }
        }
        notifyDataSetChanged()
    }

    fun getCountParticipant():Int = _listSocialAssistance.size

    private fun add(model: ModelDataHelpProgramParticipant) {
        _listSocialAssistance.add(model)
        notifyItemInserted(_listSocialAssistance.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpProgramViewHolder {
        val itemBinding =
            LayoutConfirmAcceptListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return HelpProgramViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HelpProgramViewHolder, position: Int) {
        holder.bind(_listSocialAssistance[position])
    }

    override fun getItemCount(): Int = _listSocialAssistance.size

}