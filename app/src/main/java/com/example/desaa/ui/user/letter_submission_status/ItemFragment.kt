package com.example.desaa.ui.user.letter_submission_status

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.user.adapterLetterSubmission.AdapterLetter
import com.example.desaa.databinding.FragmentItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemFragment() : Fragment() {

    fun newInstance(village: Int): ItemFragment {
        val fragment = ItemFragment()
        val args = Bundle()
        args.putInt("village", village)
        fragment.arguments = args
        return fragment
    }

    var village: Int =0

    private val adapterVillage: AdapterLetter by lazy {
        AdapterLetter()
    }
    private var _binding: FragmentItemBinding? = null

    private val binding get() = _binding!!

    private val viewModelLetterSubmissionStatus: LetterSubmissionStatusViewModel by activityViewModels()

    companion object {
        val TAG = ItemFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            village = arguments?.get("village") as Int
            binding.apply {
                grup.visibility = View.VISIBLE
                listCertificate.visibility = View.INVISIBLE


                listCertificate.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = adapterVillage

                    viewModelLetterSubmissionStatus.apply {

                        CoroutineScope(Dispatchers.Main).launch {
                            when (village) {
                                1 -> {
                                    listAssistanceBombongi.observe(viewLifecycleOwner) {
                                        if (it.isNotEmpty()) {
                                            grup.visibility = View.INVISIBLE
                                            listCertificate.visibility = View.VISIBLE
                                            adapterVillage.setList(it)
                                            Log.e("1", it.toString())
                                        } else {
                                            grup.visibility = View.VISIBLE
                                            listCertificate.visibility = View.INVISIBLE
                                        }
                                    }

                                }

                                2 -> {
                                    listAssistanceTinggitto.observe(viewLifecycleOwner) {
                                        if (it.isNotEmpty()) {
                                            grup.visibility = View.INVISIBLE
                                            listCertificate.visibility = View.VISIBLE
                                            adapterVillage.setList(it)
                                            Log.e("2", it.toString())
                                        } else {
                                            grup.visibility = View.VISIBLE
                                            listCertificate.visibility = View.INVISIBLE
                                        }
                                    }
                                }

                                3 -> {
                                    listAssistanceMakkaraeng.observe(viewLifecycleOwner) {

                                        if (it.isNotEmpty()) {
                                            grup.visibility = View.INVISIBLE
                                            listCertificate.visibility = View.VISIBLE
                                            adapterVillage.setList(it)
                                            Log.e("3", it.toString())
                                        } else {
                                            grup.visibility = View.VISIBLE
                                            listCertificate.visibility = View.INVISIBLE
                                        }
                                    }
                                }

                                4 -> {
                                    listAssistancePadaelo.observe(viewLifecycleOwner) {
                                        if (it.isNotEmpty()) {
                                            grup.visibility = View.INVISIBLE
                                            listCertificate.visibility = View.VISIBLE
                                            adapterVillage.setList(it)
                                            Log.e("4", it.toString())
                                        } else {
                                            grup.visibility = View.VISIBLE
                                            listCertificate.visibility = View.INVISIBLE
                                        }
                                    }
                                }

                                5 -> {
                                    listAssistanceBugis.observe(viewLifecycleOwner) {
                                        if (it.isNotEmpty()) {
                                            grup.visibility = View.INVISIBLE
                                            listCertificate.visibility = View.VISIBLE
                                            adapterVillage.setList(it)
                                            Log.e("5", it.toString())
                                        } else {
                                            grup.visibility = View.VISIBLE
                                            listCertificate.visibility = View.INVISIBLE
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}