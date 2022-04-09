package com.example.desaa.ui.user.social_assistance_recipient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.user.AdapterSocialAssistanceBMPT
import com.example.desaa.adapter.user.AdapterSocialAssistanceJamkes
import com.example.desaa.databinding.FragmentSocialAssistanceRecipientBinding
import com.example.desaa.utils.Data

class SocialAssistanceRecipientFragment : Fragment() {

    private var _binding: FragmentSocialAssistanceRecipientBinding? = null

    private val binding get() = _binding!!

    private val adapterSocialAssistanceBMPT: AdapterSocialAssistanceBMPT by lazy {
        AdapterSocialAssistanceBMPT()
    }

    private val adapterSocialAssistanceJamkes: AdapterSocialAssistanceJamkes by lazy {
        AdapterSocialAssistanceJamkes()
    }

    private val viewModelBMPT: SocialAssistanceRecipientViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSocialAssistanceRecipientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            listSocialAssistanceBPNT.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = adapterSocialAssistanceBMPT

                viewModelBMPT.addDatalistAssistanceBnpt(Data.dataDummy)
                viewModelBMPT.listAssistanceBnpt.observe(viewLifecycleOwner) {
                    adapterSocialAssistanceBMPT.setList(it)
                }
            }

            listSocialAssistanceJAMKESMAS.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = adapterSocialAssistanceJamkes

                viewModelBMPT.addDatalistAssistanceBnpt(Data.dataDummy)
                viewModelBMPT.listAssistanceBnpt.observe(viewLifecycleOwner) {
                    adapterSocialAssistanceJamkes.setList(it)
                }
            }


        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}