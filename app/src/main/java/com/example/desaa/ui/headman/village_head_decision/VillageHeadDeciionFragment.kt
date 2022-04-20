package com.example.desaa.ui.headman.village_head_decision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.headman.AdapterVillageHeadDecision
import com.example.desaa.databinding.FragmentVillageHeadDeciionBinding
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class VillageHeadDeciionFragment : Fragment() {

    private var _binding: FragmentVillageHeadDeciionBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private val viewModelVillageHeadman: VIllageHeadDecisionViewModel by activityViewModels()

    private val adapterVillageHeadman: AdapterVillageHeadDecision by lazy {
        AdapterVillageHeadDecision()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVillageHeadDeciionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharePreferenceApp = SharePreferenceApp.getInstance(requireActivity())

        binding.apply {
            loadingVillageHeadFragment.visibility = View.VISIBLE
            listVillageHeadman.visibility = View.GONE
            textNameHeadman.visibility = View.GONE
            textVillage.visibility = View.GONE

            viewModelVillageHeadman.apply {


                listVillageHeadman.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = adapterVillageHeadman



                    CoroutineScope(Dispatchers.Main).launch {
                        val dataVillageHeadman = NetworkConfig.apiServiceAdminVillage.getDecisionHeadman(
                            "Bearer ${
                                sharePreferenceApp.getData(
                                    SharePreferenceApp.KEY_TOKEN,
                                    ""
                                )
                            }"
                        )

                        withContext(Dispatchers.IO) {
                            addVillageHeadman(dataVillageHeadman.data)
                        }

                        villageHeadman.observe(viewLifecycleOwner) {
                            adapterVillageHeadman.setList(it)
                        }

                        textNameHeadman.text = sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_HEADMAN, "")
                        textVillage.text = sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_VILLAGE, "")

                        loadingVillageHeadFragment.visibility = View.GONE
                        listVillageHeadman.visibility = View.VISIBLE
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