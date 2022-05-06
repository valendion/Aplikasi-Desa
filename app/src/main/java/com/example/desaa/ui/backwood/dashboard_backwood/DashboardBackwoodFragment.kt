package com.example.desaa.ui.backwood.dashboard_backwood

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desaa.adapter.backwood.AdapterIntroductionAccept
import com.example.desaa.adapter.backwood.AdapterIntroductionNotAccept
import com.example.desaa.databinding.FragmentDashboardBackwoodBinding
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_NAME_APARATURE
import com.example.desaa.utils.SharePreferenceApp.Companion.KEY_NAME_BACKWOOD
import com.example.desaa.utils.SharePreferenceApp.Companion.getInstance
import com.example.desaa.utils.Validation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DashboardBackwoodFragment : Fragment() {

    private var _binding: FragmentDashboardBackwoodBinding? = null

    private val binding get() = _binding!!

    private val viewModelDashboard: DashBoardBackwoodViewModel by activityViewModels()

    private lateinit var sharePreferenceApp: SharePreferenceApp

    private val adapterAccept: AdapterIntroductionAccept by lazy {
        AdapterIntroductionAccept()
    }

    private val adapterNotAccpet: AdapterIntroductionNotAccept by lazy {
        AdapterIntroductionNotAccept()
    }


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

        binding.apply {
            loadingDashboardBackwoodFragment.visibility = View.VISIBLE
            listNotAcceptBackwoods.visibility = View.INVISIBLE
            listAcceptBackwoods.visibility = View.INVISIBLE

            textVillage.text = sharePreferenceApp.getData(KEY_NAME_BACKWOOD, "")
            textNameBackwood.text = sharePreferenceApp.getData(KEY_NAME_APARATURE, "")

            viewModelDashboard.apply {

                CoroutineScope(Dispatchers.Main).launch {


                    val dataIntruductionSubmissionAcc =
                        NetworkConfig.apiServiceAdminVillage.getIntroductionSubmissionAcc(
                            "Bearer ${
                                sharePreferenceApp.getData(
                                    SharePreferenceApp.KEY_TOKEN,
                                    ""
                                )
                            }",
                            Validation.validationBackwood(
                                sharePreferenceApp.getData(
                                    KEY_NAME_BACKWOOD,
                                    ""
                                )
                            )
                        )

                    val dataIntruductionSubmissionNotAcc =
                        NetworkConfig.apiServiceAdminVillage.getIntroductionSubmissionNotAcc(
                            "Bearer ${
                                sharePreferenceApp.getData(
                                    SharePreferenceApp.KEY_TOKEN,
                                    ""
                                )
                            }",
                            Validation.validationBackwood(
                                sharePreferenceApp.getData(
                                    KEY_NAME_BACKWOOD,
                                    ""
                                )
                            )
                        )
                    withContext(Dispatchers.IO) {
                        dataIntruductionSubmissionAcc.apply {
                            addDataAccept(data)
                        }

                        dataIntruductionSubmissionNotAcc.apply {
                            addDataNotAccept(data)
                        }
                    }

                    listAcceptBackwoods.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = adapterAccept
                        acceptDatas.observe(viewLifecycleOwner) {
                            if (it.isEmpty()) {
                                listAcceptBackwoods.visibility = View.INVISIBLE
                                grupNoData1.visibility = View.VISIBLE
                            } else {
                                adapterAccept.setList(it)
                                listAcceptBackwoods.visibility = View.VISIBLE
                                grupNoData1.visibility = View.INVISIBLE
                            }

                        }
                    }

                    listNotAcceptBackwoods.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = adapterNotAccpet

                        notAcceptDatas.observe(viewLifecycleOwner) {
                            if (it.isEmpty()) {
                                listNotAcceptBackwoods.visibility = View.INVISIBLE
                                grupNoData2.visibility = View.VISIBLE
                            } else {
                                Log.e("not_accept", it.toString())
                                adapterNotAccpet.setList(it)

                                listNotAcceptBackwoods.visibility = View.VISIBLE
                                grupNoData2.visibility = View.INVISIBLE
                            }


                        }


                    }
                    loadingDashboardBackwoodFragment.visibility = View.INVISIBLE
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