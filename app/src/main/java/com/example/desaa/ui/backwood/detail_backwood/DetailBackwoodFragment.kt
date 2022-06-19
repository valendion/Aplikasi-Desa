package com.example.desaa.ui.backwood.detail_backwood

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import coil.load
import com.example.desaa.R
import com.example.desaa.databinding.FragmentDetailBackwoodBinding
import com.example.desaa.model.response.ModelDataIntroductionSubmission
import com.example.desaa.ui.backwood.dashboard_backwood.DashboardBackwoodFragment
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_NAME_BACKWOOD
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_TOKEN
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import com.example.desaa.utils.Validation
import com.jsibbold.zoomage.ZoomageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailBackwoodFragment : Fragment() {

    private var _binding: FragmentDetailBackwoodBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private lateinit var dataDetail: ModelDataIntroductionSubmission

    companion object {
        val TAG = DetailBackwoodFragment::class.java.simpleName
    }

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

        try {
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

                imageKTP.setOnClickListener {
                    addDialog()
                }

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
        }catch (e: Exception){
            Log.d(TAG, e.message.toString())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun addDialog(){
        val dialogView   = layoutInflater.inflate(R.layout.layout_zoom_image, null)

        // Inisiasi Element / Widget
        val zoomPhoto       = dialogView.findViewById<ZoomageView>(R.id.zoomPhoto)

        zoomPhoto.load(dataDetail.fotoKtp) {
            crossfade(true)
            placeholder(R.drawable.ic_image_34)
        }
        //Inisiasi Alert Dialog
        val alertBuilder = AlertDialog.Builder(requireActivity())
        alertBuilder.setView( dialogView )

        val dialog = alertBuilder.create()
        dialog.show()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}