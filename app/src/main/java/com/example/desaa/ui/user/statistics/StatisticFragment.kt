package com.example.desaa.ui.user.statistics

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.desaa.databinding.FragmentStatisticBinding
import com.example.desaa.utils.CustomWebView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StatisticFragment : Fragment() {

    private var _binding: FragmentStatisticBinding? = null

    private val binding get() = _binding!!

    private val statisticViewModel: StatisticViewModel by viewModels()

    private lateinit var customWebView: CustomWebView

    companion object {
        val TAG = StatisticFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStatisticBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {

            binding.apply {

                loadDataStatistic()

                swipeStatisticUser.setOnRefreshListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(2000)
                        swipeStatisticUser.isRefreshing = false
                        loadDataStatistic()
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

    private fun loadDataStatistic() {
        binding.apply {

            customWebView = CustomWebView(loadingDashboardFragment)
            webViewVillageArea.webViewClient = customWebView
            webViewVillageArea.settings.javaScriptEnabled = true
            webViewVillageArea.settings.domStorageEnabled = true
            val url =
                "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d31793.320088443972!2d119.5497434177944!3d-5.076989264164404!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x2dbefa370d24c795%3A0xa5397d823fa94a8b!2sTenrigangkae%2C%20Kec.%20Mandai%2C%20Kabupaten%20Maros%2C%20Sulawesi%20Selatan!5e0!3m2!1sid!2sid!4v1650271214818!5m2!1sid!2sid\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>"
            webViewVillageArea.loadData(url, "text/html", "UTF-8")

            statisticViewModel.apply {
                getStatistic()

                isLoading.observe(viewLifecycleOwner) { setLoading(it) }

                statistic.observe(viewLifecycleOwner) {

                    textValue.text = it.jumlah_penduduk.toString()
                    textValueNumberofAdults.text = it.jumlah_penduduk_dewasa.toString()
                    textValueNumberofMalePopulation.text = it.jumlah_penduduk_lk.toString()
                    textValueNumberofFemalePopulation.text = it.jumlah_penduduk_pr.toString()
                    textValueTotalPopulationABloodType.text =
                        it.jumlah_penduduk_gol_darah_a.toString()
                    textValueboxTotalPopulationBBloodType.text =
                        it.jumlah_penduduk_gol_darah_b.toString()
                    textValueTotalPopulationOBloodType.text =
                        it.jumlah_penduduk_gol_darah_o.toString()
                    textValueTotalPopulationABBloodType.text =
                        it.jumlah_penduduk_gol_darah_ab.toString()

                    textVillageWebsiteValue.text =
                        it.website_desa

                    textVillagePhoneNumberValue.text =
                        it.telepon_desa

                }
            }
        }
    }

    private fun setLoading(status: Boolean) {
        if (status) binding.loadingDashboardFragment.visibility = View.VISIBLE
        else binding.loadingDashboardFragment.visibility = View.GONE

        if (!status) binding.boxTotalPopulation.visibility = View.VISIBLE
        else binding.boxTotalPopulation.visibility = View.GONE

        if (!status) binding.boxNumberofAdults.visibility = View.VISIBLE
        else binding.boxNumberofAdults.visibility = View.GONE

        if (!status) binding.boxNumberofMalePopulation.visibility = View.VISIBLE
        else binding.boxNumberofMalePopulation.visibility = View.GONE

        if (!status) binding.boxNumberofFemalePopulation.visibility = View.VISIBLE
        else binding.boxNumberofFemalePopulation.visibility = View.GONE

        if (!status) binding.boxTotalPopulationABBloodType.visibility = View.VISIBLE
        else binding.boxTotalPopulationABBloodType.visibility = View.GONE

        if (!status) binding.boxTotalPopulationBBloodType.visibility = View.VISIBLE
        else binding.boxTotalPopulationBBloodType.visibility = View.GONE

        if (!status) binding.boxTotalPopulationABloodType.visibility = View.VISIBLE
        else binding.boxTotalPopulationABloodType.visibility = View.GONE

        if (!status) binding.boxTotalPopulationOBloodType.visibility = View.VISIBLE
        else binding.boxTotalPopulationOBloodType.visibility = View.GONE

        if (!status) binding.webViewVillageArea.visibility = View.VISIBLE
        else binding.webViewVillageArea.visibility = View.GONE

    }
}