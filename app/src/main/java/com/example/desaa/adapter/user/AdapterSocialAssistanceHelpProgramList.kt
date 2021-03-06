package com.example.desaa.adapter.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.R
import com.example.desaa.databinding.LayoutConfirmAcceptListBinding
import com.example.desaa.databinding.LayoutConfirmRejectedListBinding
import com.example.desaa.model.response.ModelDataHelpProgramParticipant


class AdapterSocialAssistanceHelpProgramList :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _listSocialAssistance = arrayListOf<ModelDataHelpProgramParticipant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val itemBinding =
                LayoutConfirmAcceptListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return HelpProgramViewHolder(itemBinding)
        }

        val itemBinding =
            LayoutConfirmRejectedListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HelpProgramViewHolderNotActive(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HelpProgramViewHolder -> {
                holder.bind(_listSocialAssistance[position])
            }
            is HelpProgramViewHolderNotActive -> {
                holder.bind(_listSocialAssistance[position])
            }
        }
    }

    override fun getItemCount(): Int = _listSocialAssistance.size


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

    inner class HelpProgramViewHolderNotActive(val binding: LayoutConfirmRejectedListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDataHelpProgramParticipant) {
            binding.apply {
                textTitle.text = data.namaLengkap
                textValue.text = data.nik
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (_listSocialAssistance[position].status == "Aktif") 1
        else 0
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

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpProgramViewHolder {
//        val itemBinding =
//            LayoutConfirmAcceptListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
//        return HelpProgramViewHolder(itemBinding)
//    }
//
//    override fun onBindViewHolder(holder: HelpProgramViewHolder, position: Int) {
//        holder.bind(_listSocialAssistance[position])
//    }
//
//    override fun getItemCount(): Int = _listSocialAssistance.size

}