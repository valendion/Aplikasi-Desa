package com.example.desaa.ui.user.social_assistance_recipient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.R
import com.example.desaa.adapter.user.AdapterSocialAssistanceHelpProgramList
import com.example.desaa.databinding.FragmentSocialAssistanceRecipientBinding
import com.example.desaa.model.response.ModelDataDetalSocialAssistance
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import com.example.desaa.utils.Validation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SocialAssistanceRecipientFragment : Fragment() {

    private var _binding: FragmentSocialAssistanceRecipientBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private val viewModelSocialAssistance: SocialAssistanceRecipientViewModel by activityViewModels()

    private val adapterSocial: AdapterSocialAssistanceHelpProgramList by lazy { AdapterSocialAssistanceHelpProgramList() }

    private var indexSelected = 0

    private var statusData = false

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

        sharePreferenceApp = getInstance(requireActivity())

        binding.apply {

            loadingLoadingSocialAssistanceFragment.visibility = View.VISIBLE
            inputSocialAssistance.visibility = View.INVISIBLE
            recyclerSocialAssistance.visibility = View.INVISIBLE
//            grupNoData.visibility = View.VISIBLE
            btnDetailSocialAssistance.isEnabled = false

//            if (!statusData){
//                grupNoData.visibility = View.VISIBLE
//                recyclerSocialAssistance.visibility = View.INVISIBLE
//            }else{
//                grupNoData.visibility = View.INVISIBLE
//                recyclerSocialAssistance.visibility = View.VISIBLE
//            }

            recyclerSocialAssistance.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = adapterSocial


                viewModelSocialAssistance.apply {

                    CoroutineScope(Dispatchers.Main).launch {
                        val dataListProgram =
                            NetworkConfig.apiServiceAdminVillage.getHelpProgramList()
                        val help = arrayListOf<String>()

                        withContext(Dispatchers.IO) {
                            addDataHelpPrograms(dataListProgram.data)
                        }

                        btnDetailSocialAssistance.setOnClickListener {
                            dataListProgram.data[indexSelected - 1].apply {
                                val dataDetail = ModelDataDetalSocialAssistance(
                                    describe = keterangan,
                                    originOfFunds = asalDana,
                                    helpTarget = sasaranProgram,
                                    rangeTime = "$rentangWaktuMulai sampai dengan $rentangWaktuSelesai",
                                    participant = "${adapterSocial.getCountParticipant()} orang"
                                )

                                val bundle = bundleOf("detail" to dataDetail)
                                binding.root.findNavController()
                                    .navigate(R.id.detailSocialAssitanceFragment, bundle)
                            }

                        }

                        listHelpProgram.observe(viewLifecycleOwner) {
                            it.forEach { helpData ->
                                helpData.namaProgram?.let { it1 -> help.add(it1) }
                            }
                        }

                        val adapter = ArrayAdapter(
                            requireContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            help
                        )
                        (inputSocialAssistance.editText as? AutoCompleteTextView)?.setAdapter(
                            adapter
                        )

                        loadingLoadingSocialAssistanceFragment.visibility = View.INVISIBLE

                        inputSocialAssistance.visibility = View.VISIBLE

                        if (inputSocialAssistance.editText?.text?.isNotEmpty() == true) {
                            btnDetailSocialAssistance.isEnabled = true
                            indexSelected +=1
                            CoroutineScope(Dispatchers.Main).launch {
                                val dataHelpPartisipant =
                                    NetworkConfig.apiServiceAdminVillage.getHelpProgramParticipant(
                                        Validation.validationHelpPrograam(inputSocialAssistance.editText?.text.toString())
                                    )

                                withContext(Dispatchers.IO) {
                                    addDatalistAssistance(dataHelpPartisipant.data)
                                }

                                listAssistance.observe(viewLifecycleOwner) {
                                    if (it != null) {
                                        adapterSocial.setList(it)

                                        recyclerSocialAssistance.visibility = View.VISIBLE
                                    } else {

                                        recyclerSocialAssistance.visibility = View.VISIBLE
                                    }
                                }

                            }
                        }

                        inputSocialAssistance.editText?.doOnTextChanged { text, start, before, count ->
                            statusData = true
                            indexSelected = Validation.validationHelpPrograam(text.toString())

                            btnDetailSocialAssistance.isEnabled = statusData

                            CoroutineScope(Dispatchers.Main).launch {
                                val dataHelpPartisipant =
                                    NetworkConfig.apiServiceAdminVillage.getHelpProgramParticipant(
                                        Validation.validationHelpPrograam(text.toString())
                                    )

                                withContext(Dispatchers.IO) {
                                    addDatalistAssistance(dataHelpPartisipant.data)
                                }

                                listAssistance.observe(viewLifecycleOwner) {
                                    if (it != null) {
                                        adapterSocial.setList(it)
//                                        grupNoData.visibility = View.INVISIBLE
                                        recyclerSocialAssistance.visibility = View.VISIBLE
                                    } else {
//                                        grupNoData.visibility = View.VISIBLE
                                        recyclerSocialAssistance.visibility = View.VISIBLE
                                    }
                                }

                            }


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

    override fun onResume() {
        super.onResume()
        binding.apply {
            if (!statusData) {
                btnDetailSocialAssistance.isEnabled = false
                recyclerSocialAssistance.visibility = View.VISIBLE
            } else {
                btnDetailSocialAssistance.isEnabled = true
                recyclerSocialAssistance.visibility = View.VISIBLE
            }
        }
    }

}