package com.example.desaa.ui.headman.village_head_decision

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.headman.AdapterVillageHeadDecision
import com.example.desaa.databinding.FragmentVillageHeadDeciionBinding
import com.example.desaa.utils.SharePreferenceApp


class VillageHeadDeciionFragment : Fragment() {

    private var _binding: FragmentVillageHeadDeciionBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private lateinit var viewModelVillageHeadman: VIllageHeadDecisionViewModel

    private lateinit var factoryVillageDecition: FactoryDecisionHeadman

    private val adapterVillageHeadman: AdapterVillageHeadDecision by lazy {
        AdapterVillageHeadDecision()
    }

    companion object {
        val TAG = VillageHeadDeciionFragment::class.java.simpleName
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
        try {


            binding.apply {
                loadingVillageHeadFragment.visibility = View.VISIBLE
                listVillageHeadman.visibility = View.GONE
                textNameHeadman.visibility = View.GONE
                textVillage.visibility = View.GONE

                sharePreferenceApp = SharePreferenceApp.getInstance(requireActivity())

                factoryVillageDecition = FactoryDecisionHeadman(requireActivity())

                viewModelVillageHeadman = ViewModelProvider(
                    requireActivity(),
                    factoryVillageDecition
                )[VIllageHeadDecisionViewModel::class.java]

                viewModelVillageHeadman.apply {


                    listVillageHeadman.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = adapterVillageHeadman

                        villageHeadman.observe(viewLifecycleOwner) {

                            if (it != null) {
                                grup.visibility = View.INVISIBLE
                                listVillageHeadman.visibility = View.VISIBLE
                                adapterVillageHeadman.setList(it)
                            } else {
                                grup.visibility = View.VISIBLE
                                listVillageHeadman.visibility = View.INVISIBLE
                            }


                        }

                        textNameHeadman.text =
                            sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_APARATURE, "")
                        textVillage.text =
                            sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_VILLAGE, "")

                        loadingVillageHeadFragment.visibility = View.GONE
                        listVillageHeadman.visibility = View.VISIBLE
                        textNameHeadman.visibility = View.VISIBLE
                        textVillage.visibility = View.VISIBLE

                    }
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}