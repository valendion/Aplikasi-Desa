package com.example.desaa.ui.headman.village_government_officials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.headman.AdapterVillageGovernmentOfficials
import com.example.desaa.databinding.FragmentVillageGovernmentOfficialsBinding
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance

class VillageGovernmentOfficialsFragment : Fragment() {

    private var _binding: FragmentVillageGovernmentOfficialsBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private lateinit var viewModelVillageGovernment: VillageGovernmentOfficialsViewModel

    private lateinit var factoryVillageGovernment: FactoryGovermentHeadman

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

        binding.apply {
            loadingVillageGovernmentFragment.visibility = View.VISIBLE
            listGovernmentOfficials.visibility = View.GONE
            textNameHeadman.visibility = View.GONE
            textVillage.visibility = View.GONE

            sharePreferenceApp = getInstance(requireActivity())

            factoryVillageGovernment = FactoryGovermentHeadman(requireActivity())

            viewModelVillageGovernment = ViewModelProvider(
                requireActivity(),
                factoryVillageGovernment
            )[VillageGovernmentOfficialsViewModel::class.java]



            viewModelVillageGovernment.apply {

                listGovernmentOfficials.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = adapterVillageGovernment

                    villageGovernment.observe(viewLifecycleOwner) {

                        if (it != null) {
                            grup.visibility = View.INVISIBLE
                            listGovernmentOfficials.visibility = View.VISIBLE
                            adapterVillageGovernment.setList(it)
                        } else {
                            grup.visibility = View.VISIBLE
                            listGovernmentOfficials.visibility = View.INVISIBLE
                        }

                    }

                    textNameHeadman.text =
                        sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_APARATURE, "")
                    textVillage.text =
                        sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_VILLAGE, "")

                    loadingVillageGovernmentFragment.visibility = View.GONE
                    listGovernmentOfficials.visibility = View.VISIBLE
                    textNameHeadman.visibility = View.VISIBLE
                    textVillage.visibility = View.VISIBLE

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