package com.example.desaa.ui.form_village_introduction_submission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desaa.R
import com.example.desaa.databinding.FragmentFormVillageIntroductionSubmissionBinding


class FormVillageIntroductionSubmissionFragment : Fragment() {

    private var _binding: FragmentFormVillageIntroductionSubmissionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentFormVillageIntroductionSubmissionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}