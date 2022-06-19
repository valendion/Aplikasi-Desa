package com.example.desaa.ui.user.detail_social_assistance

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.desaa.databinding.FragmentDetailSocialAssitanceBinding
import com.example.desaa.model.response.ModelDataDetalSocialAssistance
import com.example.desaa.ui.login.LoginActivity

class DetailSocialAssitanceFragment : Fragment() {

    private var _binding :FragmentDetailSocialAssitanceBinding? = null

    val binding get() = _binding!!

    private lateinit var dataDetail: ModelDataDetalSocialAssistance

    companion object {
        val TAG = DetailSocialAssitanceFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataDetail = it.getParcelable("detail")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailSocialAssitanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            binding.apply {
                textDescribeSocialAssistance.text = dataDetail.describe
                textValueAidProgramGoals.text = dataDetail.helpTarget
                textValueHelpTimeline.text = dataDetail.rangeTime
                textValueOriginofFunds.text = dataDetail.originOfFunds
                textValueNumberofBeneficiaries.text = dataDetail.participant
            }
        }catch (e: Exception){
            Log.d(TAG, e.message.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}