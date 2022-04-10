package com.example.desaa.ui.user.letter_submission_status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.user.AdapterVillageIntrocutionCertificate
import com.example.desaa.databinding.FragmentLetterSubmissionStatusBinding
import com.example.desaa.ui.user.village_introduction_submission.VillageIntroductionSubmissionViewModel
import com.example.desaa.utils.Data


class LetterSubmissionStatusFragment : Fragment() {

    private var _binding: FragmentLetterSubmissionStatusBinding? = null

    private val binding get() = _binding!!

    private val viewModelLetterSubmissionStatus: LetterSubmissionStatusViewModel by activityViewModels()

    private val adapterVillageIntrucduction : AdapterVillageIntrocutionCertificate by lazy {
        AdapterVillageIntrocutionCertificate()
    }
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
            listVillageIntroductionCertificate.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = adapterVillageIntrucduction

                viewModelLetterSubmissionStatus.addDatalistVillageIntroductionCertificate(Data.dataDummy)
                viewModelLetterSubmissionStatus.listAssistanceBnpt.observe(viewLifecycleOwner) {
                    adapterVillageIntrucduction.setList(it)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}