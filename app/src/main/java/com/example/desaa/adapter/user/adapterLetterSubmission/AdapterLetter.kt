package com.example.desaa.adapter.user.adapterLetterSubmission

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.databinding.LayoutConfirmNotPrintedListBinding
import com.example.desaa.databinding.LayoutConfirmPrintedListBinding
import com.example.desaa.model.response.ModelDataIntroductionVillageLetter


class AdapterLetter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _listSocialAssistance = arrayListOf<ModelDataIntroductionVillageLetter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val itemBinding =
                LayoutConfirmPrintedListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return HelpProgramViewHolder(itemBinding)
        }

        val itemBinding =
            LayoutConfirmNotPrintedListBinding.inflate(
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

    inner class HelpProgramViewHolder(val binding: LayoutConfirmPrintedListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDataIntroductionVillageLetter) {
            binding.apply {
                textTitle.text = data.namaPenduduk
                textValue.text = data.nik
                textValueCertificate.text = data.jenisSuratAkanDibuat
            }
        }
    }

    inner class HelpProgramViewHolderNotActive(val binding: LayoutConfirmNotPrintedListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDataIntroductionVillageLetter) {
            binding.apply {
                textTitle.text = data.namaPenduduk
                textValue.text = data.nik
                textValueCertificate.text = data.jenisSuratAkanDibuat
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (_listSocialAssistance[position].statusAcc == "ACC") 1
        else 0
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<ModelDataIntroductionVillageLetter>) {
        _listSocialAssistance.clear()
        list.forEach { model ->
            if (model != null) {
                add(model)
            }
        }
        notifyDataSetChanged()
    }

    private fun add(model: ModelDataIntroductionVillageLetter) {
        _listSocialAssistance.add(model)
        notifyItemInserted(_listSocialAssistance.size)
    }

}