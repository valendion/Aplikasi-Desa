package com.example.desaa.ui.user.letter_submission_status

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.user.adapterLetterSubmission.*
import com.example.desaa.databinding.FragmentLetterSubmissionStatusBinding
import com.example.desaa.model.response.ModelDataIntroductionVillageLetter
import com.example.desaa.utils.NetworkConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LetterSubmissionStatusFragment : Fragment() {

    private var _binding: FragmentLetterSubmissionStatusBinding? = null

    private val binding get() = _binding!!

    private val viewModelLetterSubmissionStatus: LetterSubmissionStatusViewModel by activityViewModels()

    private val adapterVillageBombongi: AdapterLetterBombongi by lazy {
        AdapterLetterBombongi()
    }

    private val adapterVillageTinggitto: AdapterLetterTinggitto by lazy {
        AdapterLetterTinggitto()
    }

    private val adapterLetterPadaelo: AdapterLetterPadaelo by lazy {
        AdapterLetterPadaelo()
    }

    private val adapterLetterMakkaraeng: AdapterLetterMakkaraeng by lazy {
        AdapterLetterMakkaraeng()
    }

    private val adapterLetterBugis: AdapterLetterBugis by lazy {
        AdapterLetterBugis()
    }

    private val dataBombongi = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataTinggitto = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataMakkaraeng = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataPadaelo = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataBugis = arrayListOf<ModelDataIntroductionVillageLetter>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLetterSubmissionStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            loadingLoadingLetterSubmissionFragment.visibility = View.VISIBLE
            grup1.visibility = View.VISIBLE
            grup2.visibility = View.VISIBLE
            grup3.visibility = View.VISIBLE
            grup4.visibility = View.VISIBLE
            grup5.visibility = View.VISIBLE
            listBugisCertificate.visibility = View.INVISIBLE
            listPadaeloCertificate.visibility = View.INVISIBLE
            listMakkaraengCertificate.visibility = View.INVISIBLE
            listBombongiCertificate.visibility = View.INVISIBLE
            listTinggittoCertificate.visibility = View.INVISIBLE

            viewModelLetterSubmissionStatus.apply {

                CoroutineScope(Dispatchers.Main).launch {
                    val dataVillage =
                        NetworkConfig.apiServiceAdminVillage.getIntroductionVillageLetterList()

                    dataVillage.data.forEach { data ->
                        if (data.dusunId == 1) {
                            dataBombongi.add(data)
                        }
                        if (data.dusunId == 2) {
                            dataTinggitto.add(data)
                        }
                        if (data.dusunId == 3) {
                            dataMakkaraeng.add(data)
                        }
                        if (data.dusunId == 4) {
                            dataPadaelo.add(data)
                        }
                        if (data.dusunId == 5) {
                            dataBugis.add(data)
                        }
                    }

                    listTinggittoCertificate.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = adapterVillageTinggitto

                        CoroutineScope(Dispatchers.IO).launch {
                            addDatalistVillageTinggittoIntroductionCertificate(
                                dataTinggitto
                            )
                        }

                        listAssistanceTinggitto.observe(viewLifecycleOwner) {
                            adapterVillageTinggitto.setList(it)
                        }
                    }

                    listBombongiCertificate.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = adapterVillageBombongi

                        CoroutineScope(Dispatchers.IO).launch {
                            addDatalistVillageBombongiIntroductionCertificate(
                                dataBombongi
                            )
                        }

                        listAssistanceBombongi.observe(viewLifecycleOwner) {
                            if (it.isNotEmpty()){
                                grup1.visibility = View.INVISIBLE
                                listBombongiCertificate.visibility = View.VISIBLE

                                adapterVillageBombongi.setList(it)
                            }else {
                                grup1.visibility = View.VISIBLE
                                listBombongiCertificate.visibility = View.INVISIBLE
                            }
                        }
                    }


                    listPadaeloCertificate.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = adapterLetterPadaelo

                        CoroutineScope(Dispatchers.IO).launch {
                            addDatalistVillagePadaeloIntroductionCertificate(
                                dataPadaelo
                            )
                        }

                        listAssistancePadaelo.observe(viewLifecycleOwner) {
                            if (it.isNotEmpty()){
                                grup4.visibility = View.INVISIBLE
                                listPadaeloCertificate.visibility = View.VISIBLE

                                adapterLetterPadaelo.setList(it)
                            }else {
                                grup4.visibility = View.VISIBLE
                                listPadaeloCertificate.visibility = View.INVISIBLE
                            }
                        }
                    }

                    listMakkaraengCertificate.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = adapterLetterMakkaraeng

                        CoroutineScope(Dispatchers.IO).launch {
                            addDatalistVillageMakkaraengIntroductionCertificate(
                                dataMakkaraeng
                            )
                        }

                        listAssistanceMakkaraeng.observe(viewLifecycleOwner) {

                            if (it.isNotEmpty()){
                                grup3.visibility = View.INVISIBLE
                                listMakkaraengCertificate.visibility = View.VISIBLE

                                adapterLetterMakkaraeng.setList(it)
                            }else {
                                grup3.visibility = View.VISIBLE
                                listMakkaraengCertificate.visibility = View.INVISIBLE
                            }
                        }

                    }

                    listBugisCertificate.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = adapterLetterBugis

                        CoroutineScope(Dispatchers.IO).launch {
                            addDatalistVillageBugisIntroductionCertificate(
                                dataBugis
                            )
                        }

                        listAssistanceBugis.observe(viewLifecycleOwner) {

                            if (it.isNotEmpty()){
                                grup5.visibility = View.INVISIBLE
                                listBugisCertificate.visibility = View.VISIBLE
                                adapterLetterBugis.setList(it)
                            }else {
                                grup5.visibility = View.VISIBLE
                                listBugisCertificate.visibility = View.INVISIBLE
                            }
                        }
                    }

                    loadingLoadingLetterSubmissionFragment.visibility = View.INVISIBLE


                }




            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}