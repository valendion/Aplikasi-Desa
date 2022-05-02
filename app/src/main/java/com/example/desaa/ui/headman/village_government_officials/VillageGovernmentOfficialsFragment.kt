package com.example.desaa.ui.headman.village_government_officials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.headman.AdapterVillageGovernmentOfficials
import com.example.desaa.databinding.FragmentVillageGovernmentOfficialsBinding
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_TOKEN
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VillageGovernmentOfficialsFragment : Fragment() {

    private var _binding: FragmentVillageGovernmentOfficialsBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private val viewModelVillageGovernment: VillageGovernmentOfficialsViewModel by activityViewModels()

    private val adapterVillageGovernment: AdapterVillageGovernmentOfficials by lazy {
        AdapterVillageGovernmentOfficials()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVillageGovernmentOfficialsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharePreferenceApp = getInstance(requireActivity())
        binding.apply {
            loadingVillageGovernmentFragment.visibility = View.VISIBLE
            listGovernmentOfficials.visibility = View.GONE
            textNameHeadman.visibility = View.GONE
            textVillage.visibility = View.GONE

            viewModelVillageGovernment.apply {

                listGovernmentOfficials.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = adapterVillageGovernment

                    CoroutineScope(Dispatchers.Main).launch {
                        val dataVIllageGovernment = NetworkConfig.apiServiceAdminVillage.getVillageGovernmentOfficials(
                            "Bearer ${
                                sharePreferenceApp.getData(
                                    KEY_TOKEN,
                                    ""
                                )
                            }"
                        )

                        withContext(Dispatchers.IO) {
                            addVillageGovernment(dataVIllageGovernment.data)
                        }

                        villageGovernment.observe(viewLifecycleOwner) {
                            adapterVillageGovernment.setList(it)
                        }

                        textNameHeadman.text = sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_APARATURE, "")
                        textVillage.text = sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_VILLAGE, "")

                        loadingVillageGovernmentFragment.visibility = View.GONE
                        listGovernmentOfficials.visibility = View.VISIBLE
                        textNameHeadman.visibility = View.VISIBLE
                        textVillage.visibility = View.VISIBLE
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}