package com.example.desaa.ui.user.letter_submission_status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.desaa.adapter.user.adapterLetterSubmission.AdapterLetter
import com.example.desaa.databinding.FragmentLetterSubmissiionBinding
import com.example.desaa.model.response.ModelDataIntroductionVillageLetter
import com.example.desaa.utils.Constant
import com.example.desaa.utils.NetworkConfig
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LetterSubmissiionFragment : Fragment() {
    private var _binding: FragmentLetterSubmissiionBinding? = null

    private val binding get() = _binding!!

    private val dataBombongi = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataTinggitto = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataMakkaraeng = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataPadaelo = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataBugis = arrayListOf<ModelDataIntroductionVillageLetter>()

    private val viewModelLetterSubmissionStatus: LetterSubmissionStatusViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentLetterSubmissiionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewModelLetterSubmissionStatus.apply {
                CoroutineScope(Dispatchers.IO).launch {
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


                    addDatalistVillageBombongiIntroductionCertificate(
                        dataBombongi
                    )

                    addDatalistVillageTinggittoIntroductionCertificate(
                        dataTinggitto
                    )

                    addDatalistVillagePadaeloIntroductionCertificate(
                        dataPadaelo
                    )

                    addDatalistVillageMakkaraengIntroductionCertificate(
                        dataMakkaraeng
                    )

                    addDatalistVillageBugisIntroductionCertificate(
                        dataBugis
                    )

                    withContext(Dispatchers.Main){
                        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)
                        viewPager2.adapter = adapter

                        TabLayoutMediator(tabLayout, viewPager2){tab, position ->
                            tab.text = Constant.NAME_TABS[position]
                        }.attach()

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