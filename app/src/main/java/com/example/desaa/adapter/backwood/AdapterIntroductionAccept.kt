package com.example.desaa.adapter.backwood

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.R
import com.example.desaa.databinding.LayoutConfirmAcceptListBinding
import com.example.desaa.model.response.ModelDataIntroductionSubmission

class AdapterIntroductionAccept :
    RecyclerView.Adapter<AdapterIntroductionAccept.AcceptViewModel>() {
    private var _listSocialAssistance = arrayListOf<ModelDataIntroductionSubmission>()

    inner class AcceptViewModel(val binding: LayoutConfirmAcceptListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDataIntroductionSubmission) {
            binding.apply {
                textTitle.text = data.namaPenduduk
                textValue.text = data.nik
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<ModelDataIntroductionSubmission>) {
        _listSocialAssistance.clear()
        list.forEach { model ->
            if (model != null) {
                add(model)
            }
        }

        notifyDataSetChanged()
    }

    private fun add(model: ModelDataIntroductionSubmission) {
        _listSocialAssistance.add(model)
        notifyItemInserted(_listSocialAssistance.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcceptViewModel {
        val itemBinding =
            LayoutConfirmAcceptListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcceptViewModel(itemBinding)
    }

    override fun onBindViewHolder(holder: AcceptViewModel, position: Int) {
        holder.bind(_listSocialAssistance[position])
    }

    override fun getItemCount(): Int = _listSocialAssistance.size
}