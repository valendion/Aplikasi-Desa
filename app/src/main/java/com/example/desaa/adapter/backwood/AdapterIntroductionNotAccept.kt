package com.example.desaa.adapter.backwood

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.R
import com.example.desaa.databinding.LayoutConfirmCheckListBinding

import com.example.desaa.model.response.ModelDataIntroductionSubmission

class AdapterIntroductionNotAccept :
    RecyclerView.Adapter<AdapterIntroductionNotAccept.NotAcceptViewModel>() {
    private var _listSocialAssistance = arrayListOf<ModelDataIntroductionSubmission>()

    inner class NotAcceptViewModel(val binding: LayoutConfirmCheckListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDataIntroductionSubmission) {
            binding.apply {
                textTitle.text = data.namaPenduduk
                textValue.text = data.nik

                textStatus.setOnClickListener{
                    val bundle = bundleOf("detailBackwood" to data)
                    binding.root.findNavController()
                        .navigate(R.id.nav_detail_backwood_Fragment, bundle)
                }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotAcceptViewModel {
        val itemBinding =
            LayoutConfirmCheckListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotAcceptViewModel(itemBinding)
    }

    override fun onBindViewHolder(holder: NotAcceptViewModel, position: Int) {
        holder.bind(_listSocialAssistance[position])
    }

    override fun getItemCount(): Int = _listSocialAssistance.size

}