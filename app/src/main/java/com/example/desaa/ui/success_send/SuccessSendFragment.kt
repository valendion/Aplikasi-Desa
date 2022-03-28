package com.example.desaa.ui.success_send

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desaa.R
import com.example.desaa.databinding.FragmentSlideshowBinding
import com.example.desaa.databinding.FragmentSuccessSendBinding
import com.example.desaa.databinding.FragmentVillageIntroductionSubmissionBinding


class SuccessSendFragment : Fragment() {

    private var _binding: FragmentSuccessSendBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessSendBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.root.setDisplayHomeAsUpEnabled(false)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}