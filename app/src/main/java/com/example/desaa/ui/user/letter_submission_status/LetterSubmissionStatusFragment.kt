package com.example.desaa.ui.user.letter_submission_status

import android.os.Bundle
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
//                        withContext(Dispatchers.IO) {
//                            addDatalistVillageBombongiIntroductionCertificate(
//                                dataBombongi
//                            )
//                        }
//
//                        listAssistanceBombongi.observe(viewLifecycleOwner) {
//                            adapterVillageBombongi.setList(it)
//                        }
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
                            adapterVillageBombongi.setList(it)
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
                            adapterLetterPadaelo.setList(it)
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
                            adapterLetterMakkaraeng.setList(it)
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
                            adapterLetterBugis.setList(it)
                        }
                    }

                }




            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}