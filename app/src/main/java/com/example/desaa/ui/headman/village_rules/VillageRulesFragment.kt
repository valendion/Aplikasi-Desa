package com.example.desaa.ui.headman.village_rules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.headman.AdapterVillageRule
import com.example.desaa.databinding.FragmentVillageRulesBinding
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_NAME_HEADMAN
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_NAME_VILLAGE
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_TOKEN
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VillageRulesFragment : Fragment() {

    private var _binding: FragmentVillageRulesBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private val viewModelVillageRule: VillageRulesViewModel by activityViewModels()

    private val adapterVillageRule: AdapterVillageRule by lazy {
        AdapterVillageRule()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVillageRulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharePreferenceApp = getInstance(requireActivity())

        binding.apply {
            loadingVillageRuleFragment.visibility = View.VISIBLE
            recyclerviewVillageRule.visibility = View.GONE
            textNameHeadman.visibility = View.GONE
            textVillage.visibility = View.GONE

            viewModelVillageRule.apply {


                recyclerviewVillageRule.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = adapterVillageRule



                    CoroutineScope(Dispatchers.Main).launch {
                        val dataVillageRule = NetworkConfig.apiServiceAdminVillage.getRuleVillage(
                            "Bearer ${
                                sharePreferenceApp.getData(
                                    KEY_TOKEN,
                                    ""
                                )
                            }"
                        )

                        withContext(Dispatchers.IO) {
                            addVillageRule(dataVillageRule.data)


                        }

                        villageRule.observe(viewLifecycleOwner) {
                            adapterVillageRule.setList(it)
                        }

                        textNameHeadman.text = sharePreferenceApp.getData(KEY_NAME_HEADMAN, "")
                        textVillage.text = sharePreferenceApp.getData(KEY_NAME_VILLAGE, "")

                        loadingVillageRuleFragment.visibility = View.GONE
                        recyclerviewVillageRule.visibility = View.VISIBLE
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