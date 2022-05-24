package com.example.desaa.ui.user.village_introduction_submission

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.desaa.R
import com.example.desaa.databinding.FragmentVillageIntroductionSubmissionBinding
import com.example.desaa.model.response.ResponseStatus
import com.example.desaa.utils.Convert
import com.example.desaa.utils.NetworkConfig
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody


class VillageIntroductionSubmissionFragment : Fragment() {

    private var _binding: FragmentVillageIntroductionSubmissionBinding? = null

    private val binding get() = _binding!!

    private var uri: Uri? = null

    private var dataTypeLetter = arrayListOf<String>()

    private var TAG = "VillageIntroductionSubmissionFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVillageIntroductionSubmissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        factoryVillageIntroduction = VillageIntroductionSubmissionFactory()
//        viewModelVillageIntroduction = ViewModelProvider(this, factoryVillageIntroduction)[VillageIntroductionSubmissionViewModel::class.java]

        binding.apply {

            loadingLoadingVillageIntroductionFragment.visibility = View.VISIBLE

            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                loadTypeLetter()
            )
            (inputLetterType.editText as? AutoCompleteTextView)?.setAdapter(
                adapter
            )

            loadingLoadingVillageIntroductionFragment.visibility = View.INVISIBLE

            btnSend.setOnClickListener {

                CoroutineScope(Dispatchers.Main).launch {

                    val nik = inputNik.editText?.text.toString()


                    val jenisSurat = inputLetterType.editText?.text.toString()
                    val reason = inputDescription.editText?.text.toString()

                    when {
                        nik.isEmpty() -> {
                            inputNik.error = "Nik anda kosong"
                            inputNik.requestFocus()
                        }
                        jenisSurat.isEmpty() -> {
                            inputLetterType.error = "Jenis Surat anda kosong"
                            inputNik.requestFocus()
                        }
                        reason.isEmpty() -> {
                            inputDescription.error = "Alasan anda  kosong"
                            inputNik.requestFocus()
                        }
                        uri == null -> {
                            SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Perhatian")
                                .setContentText("Gambar anda kosong")
                                .show()
                        }
                        else -> {
                            inputNik.error = null
                            inputLetterType.error = null
                            inputDescription.error = null


                            val file = Convert.getFileFromUri(requireActivity(), uri!!)

                            val requestBody =
                                file?.let { it1 ->
                                    RequestBody.create(
                                        "image/*".toMediaTypeOrNull(),
                                        it1
                                    )
                                }

                            val part = file?.let { it1 ->
                                requestBody?.let { it2 ->
                                    MultipartBody.Part.createFormData(
                                        "foto_ktp", it1.name,
                                        it2
                                    )
                                }
                            }

                            val requestNik =
                                RequestBody.create("text/plain".toMediaTypeOrNull(), nik)
                            val requestJenisSurat =
                                RequestBody.create("text/plain".toMediaTypeOrNull(), jenisSurat)
                            val requestReason =
                                RequestBody.create("text/plain".toMediaTypeOrNull(), reason)

                            withContext(Dispatchers.IO) {
                                val response =
                                    part?.let { it1 ->
                                        NetworkConfig.apiServiceAdminVillage.postFormUser(
                                            requestNik, requestJenisSurat, requestReason, it1
                                        )
                                    }

                                withContext(Dispatchers.Main) {

                                    if (response!!.isSuccessful) {
                                        SweetAlertDialog(
                                            requireActivity(),
                                            SweetAlertDialog.SUCCESS_TYPE
                                        )
                                            .setTitleText("Berhasil")
                                            .setContentText(response.body()?.message ?: "")
                                            .show()

                                        val action =
                                            VillageIntroductionSubmissionFragmentDirections.actionNavVillageToSuccessSendFragment()
                                        Navigation.findNavController(binding.root).navigate(action)
                                    } else {
                                        val errorMessage = Gson().fromJson(
                                            response.errorBody()?.charStream(),
                                            ResponseStatus::class.java
                                        )
                                        SweetAlertDialog(
                                            requireActivity(),
                                            SweetAlertDialog.WARNING_TYPE
                                        )
                                            .setTitleText("Perhatian")
                                            .setContentText(errorMessage.message)
                                            .setConfirmText("Perbaharui Data Form")
                                            .show()
                                    }
                                }

                            }
                        }


                    }
                }
            }

            constraintAddPhoto.setOnClickListener {
                ImagePicker.with(this@VillageIntroductionSubmissionFragment)
                    .cameraOnly()
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(
                        1080,
                        1080
                    )    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            uri = data?.data!!

            // Use Uri object instead of File to avoid storage permissions
            binding.imageValue.setImageURI(uri)


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Foto gagal", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadTypeLetter(): ArrayList<String> {
        CoroutineScope(Dispatchers.IO).launch {
            val data =
                NetworkConfig.apiServiceAdminVillage.getTypeLetter()

            data.data?.forEach {
                it.namaJenisSurat?.let { it1 -> dataTypeLetter.add(it1) }
            }
        }
        return dataTypeLetter
    }

}