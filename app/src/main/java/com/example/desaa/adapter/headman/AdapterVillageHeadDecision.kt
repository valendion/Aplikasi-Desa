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
import androidx.recyclerview.widget.RecyclerView
import com.example.desaa.databinding.LayoutVillageDecisionListBinding
import com.example.desaa.model.response.ModelDataDecisionHeadman

class AdapterVillageHeadDecision :
    RecyclerView.Adapter<AdapterVillageHeadDecision.VillageHeadDecision>() {
    private var _listSocialAssistance = arrayListOf<ModelDataDecisionHeadman>()

    inner class VillageHeadDecision(val binding: LayoutVillageDecisionListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDataDecisionHeadman) {
            binding.apply {
                textTitle.text = data.judulDokumen
                textValueDate.text = data.tanggalKeputusanKepalaDesa
                textValueNumber.text = data.nomorKeputusanKepalaDesa

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
    fun setList(list: ArrayList<ModelDataDecisionHeadman>?) {
        list?.forEach { model ->
            if (model != null) {
                add(model)
            }
        }
    }

    private fun add(model: ModelDataDecisionHeadman) {
        _listSocialAssistance.add(model)
        notifyItemInserted(_listSocialAssistance.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VillageHeadDecision {
        val itemBinding =
            LayoutVillageDecisionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VillageHeadDecision(itemBinding)
    }

    override fun onBindViewHolder(holder: VillageHeadDecision, position: Int) {
        holder.bind(_listSocialAssistance[position])
    }

    override fun getItemCount(): Int = _listSocialAssistance.size

}