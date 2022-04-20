package com.example.desaa.adapter.headman

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.databinding.LayoutVillageRulesListBinding
import com.example.desaa.model.response.ModelDataRuleVillage

class AdapterVillageRule :
    RecyclerView.Adapter<AdapterVillageRule.VillageRuleHolder>() {
    private var _listSocialAssistance = arrayListOf<ModelDataRuleVillage>()

    inner class VillageRuleHolder(val binding: LayoutVillageRulesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDataRuleVillage) {
            binding.apply {
                textTitle.text = data.judulDokumen
                textValueDate.text = data.tanggalDitetapkan
                btnDownload.setOnClickListener {
                    val request = DownloadManager.Request(Uri.parse(data.dokumen))
                    val title = URLUtil.guessFileName(data.dokumen, null, null)
                    request.setTitle(title)
                    request.setDescription("Download File please wait...")
                    val cookie = CookieManager.getInstance().getCookie(data.dokumen)
                    request.addRequestHeader("cookie", cookie)
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title)

                    val downloadManager = itemView.context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                    downloadManager.enqueue(request)

                    Toast.makeText(itemView.context, "Download Started", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<ModelDataRuleVillage>?) {
        list?.forEach { model ->
            if (model != null) {
                add(model)
            }
        }
    }

    private fun add(model: ModelDataRuleVillage) {
        _listSocialAssistance.add(model)
        notifyItemInserted(_listSocialAssistance.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VillageRuleHolder {
        val itemBinding =
            LayoutVillageRulesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VillageRuleHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: VillageRuleHolder, position: Int) {
        holder.bind(_listSocialAssistance[position])
    }

    override fun getItemCount(): Int = _listSocialAssistance.size

}