package com.example.desaa.ui.backwood

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.desaa.databinding.FragmentApproveBinding

class ApproveFragment : Fragment() {

    private var _binding: FragmentApproveBinding? = null

    private val binding get() = _binding!!


    private val messgeApprove: ApproveFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentApproveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val text = messgeApprove.dataMessage
        binding.apply {
            textDescribeApprpve.text = text
            btnBackDashboard.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}