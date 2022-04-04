package com.example.desaa.ui.user.village_introduction_submission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.desaa.databinding.FragmentVillageIntroductionSubmissionBinding


class VillageIntroductionSubmissionFragment : Fragment() {

    private var _binding: FragmentVillageIntroductionSubmissionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVillageIntroductionSubmissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            btnSend.setOnClickListener {
                val direction =
                    VillageIntroductionSubmissionFragmentDirections.actionNavVillageToSuccessSendFragment()
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