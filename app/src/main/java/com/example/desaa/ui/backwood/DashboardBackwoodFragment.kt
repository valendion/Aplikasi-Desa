package com.example.desaa.ui.backwood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.desaa.databinding.FragmentDashboardBackwoodBinding


class DashboardBackwoodFragment : Fragment() {

    private var _binding: FragmentDashboardBackwoodBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment


        _binding = FragmentDashboardBackwoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            textTitleVillageBackwood.setOnClickListener {
                val direction =
                    DashboardBackwoodFragmentDirections.actionNavDashboardBackwoodFragmentToNavDetailBackwoodFragment()
                Navigation.findNavController(view).navigate(direction)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}