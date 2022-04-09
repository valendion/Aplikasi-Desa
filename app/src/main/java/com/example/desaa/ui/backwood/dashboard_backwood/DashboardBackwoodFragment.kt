package com.example.desaa.ui.backwood.dashboard_backwood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.desaa.databinding.FragmentDashboardBackwoodBinding
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance


class DashboardBackwoodFragment : Fragment() {

    private var _binding: FragmentDashboardBackwoodBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModelDashboardBackwood: DashBoardBackwoodViewModel

    private lateinit var sharePreferenceApp: SharePreferenceApp


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBackwoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharePreferenceApp = getInstance(requireActivity())

        val viewModelFactory =
            DashBoardViewModelFactory(
                requireActivity().application,
                sharePreferenceApp
            )
        viewModelDashboardBackwood =
            ViewModelProvider(this, viewModelFactory)[DashBoardBackwoodViewModel::class.java]

        viewModelDashboardBackwood.role.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        binding.apply {

            textTitleVillageBackwood.setOnClickListener {
                viewModelDashboardBackwood.changevalue()
//                val direction =
//                    DashboardBackwoodFragmentDirections.actionNavDashboardBackwoodFragmentToNavDetailBackwoodFragment()
//                Navigation.findNavController(view).navigate(direction)
            }

        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}