package com.example.desaa.ui.headman.village_rules

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.headman.AdapterVillageRule
import com.example.desaa.databinding.FragmentVillageRulesBinding
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_NAME_APARATURE
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_NAME_VILLAGE
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance

class VillageRulesFragment : Fragment() {

    private var _binding: FragmentVillageRulesBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private lateinit var viewModelVillageRule: VillageRulesViewModel

    private lateinit var factoryVillageRule: FactoryRulesHeadman

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


    companion object {
        val TAG = VillageRulesFragment::class.java.simpleName
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        try {

            binding.apply {
                loadingVillageRuleFragment.visibility = View.VISIBLE
                recyclerviewVillageRule.visibility = View.GONE
                textNameHeadman.visibility = View.GONE
                textVillage.visibility = View.GONE

                sharePreferenceApp = getInstance(requireActivity())

                factoryVillageRule = FactoryRulesHeadman(requireActivity())

                viewModelVillageRule = ViewModelProvider(
                    requireActivity(),
                    factoryVillageRule
                )[VillageRulesViewModel::class.java]

                viewModelVillageRule.apply {

                    recyclerviewVillageRule.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = adapterVillageRule

                        villageRule.observe(viewLifecycleOwner) {
                            if (it != null) {
                                grup.visibility = View.INVISIBLE
                                recyclerviewVillageRule.visibility = View.VISIBLE
                                adapterVillageRule.setList(it)
                            } else {
                                grup.visibility = View.VISIBLE
                                recyclerviewVillageRule.visibility = View.INVISIBLE
                            }
                        }

                        textNameHeadman.text = sharePreferenceApp.getData(KEY_NAME_APARATURE, "")
                        textVillage.text = sharePreferenceApp.getData(KEY_NAME_VILLAGE, "")

                        loadingVillageRuleFragment.visibility = View.GONE
                        recyclerviewVillageRule.visibility = View.VISIBLE
                        textNameHeadman.visibility = View.VISIBLE
                        textVillage.visibility = View.VISIBLE
                    }
                }
            }
        }catch (e: Exception){
            Log.d(TAG, e.message.toString())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}