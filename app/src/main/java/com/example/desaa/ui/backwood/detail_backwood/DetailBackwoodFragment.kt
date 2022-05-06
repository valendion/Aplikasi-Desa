package com.example.desaa.ui.backwood.detail_backwood

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import coil.load
import com.example.desaa.R
import com.example.desaa.databinding.FragmentDetailBackwoodBinding
import com.example.desaa.model.response.ModelDataIntroductionSubmission
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_NAME_BACKWOOD
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_TOKEN
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import com.example.desaa.utils.Validation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailBackwoodFragment : Fragment() {

    private var _binding: FragmentDetailBackwoodBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private lateinit var dataDetail: ModelDataIntroductionSubmission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataDetail = it.getParcelable("detailBackwood")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentDetailBackwoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharePreferenceApp = getInstance(requireActivity())
        binding.apply {
            textValueDetailNik.text = dataDetail.nik
            textValueDetailApplicantName.text = dataDetail.namaPenduduk
            textValueDetailManagedMail.text = dataDetail.jenisSuratAkanDibuat
            textValueDetailDescription.text = dataDetail.keterangan
            imageKTP.load(dataDetail.fotoKtp) {
                crossfade(true)
                placeholder(R.drawable.ic_image_34)
            }

            dataDetail.fotoKtp?.let { Log.e("image", it) }

            btnConfirm.setOnClickListener {

                CoroutineScope(Dispatchers.Main).launch {
                    val dataMessage =
                        dataDetail.nik?.let { it1 ->
                            dataDetail.jenisSuratAkanDibuat?.let { it2 ->
                                NetworkConfig.apiServiceAdminVillage.getMessageApprove(
                                    "Bearer ${
                                        sharePreferenceApp.getData(
                                            KEY_TOKEN,
                                            ""
                                        )
                                    }",
                                    Validation.validationBackwood(
                                        sharePreferenceApp.getData(
                                            KEY_NAME_BACKWOOD,
                                            ""
                                        )
                                    ),
                                    it1,
                                    it2
                                )
                            }
                        }
                    val message = dataMessage?.message
                    val action = DetailBackwoodFragmentDirections.actionNavDetailBackwoodFragmentToNavApproveFragment(message)
                    Navigation.findNavController(binding.root).navigate(action)
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}