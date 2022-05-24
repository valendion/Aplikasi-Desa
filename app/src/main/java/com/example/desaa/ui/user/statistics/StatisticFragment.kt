package com.example.desaa.ui.user.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.desaa.R
import com.example.desaa.databinding.FragmentDashboardBinding
import com.example.desaa.databinding.FragmentStatisticBinding
import com.example.desaa.ui.headman.dashboard.DashboardViewModel

class StatisticFragment : Fragment() {

    private var _binding: FragmentStatisticBinding? = null

    private val binding get() = _binding!!

    private val statisticViewModel: StatisticViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStatisticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            loadingDashboardFragment.visibility = View.VISIBLE
            boxTotalPopulation.visibility = View.GONE
            boxNumberofAdults.visibility = View.GONE
            boxNumberofMalePopulation.visibility = View.GONE
            boxNumberofFemalePopulation.visibility = View.GONE
            boxTotalPopulationABBloodType.visibility = View.GONE
            boxTotalPopulationBBloodType.visibility = View.GONE
            boxTotalPopulationABloodType.visibility = View.GONE
            boxTotalPopulationOBloodType.visibility = View.GONE

            val url =
                "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d31793.320088443972!2d119.5497434177944!3d-5.076989264164404!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x2dbefa370d24c795%3A0xa5397d823fa94a8b!2sTenrigangkae%2C%20Kec.%20Mandai%2C%20Kabupaten%20Maros%2C%20Sulawesi%20Selatan!5e0!3m2!1sid!2sid!4v1650271214818!5m2!1sid!2sid\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>"
            webViewVillageArea.settings.javaScriptEnabled = true
            webViewVillageArea.loadData(url, "text/html", "UTF-8")

            statisticViewModel.apply {
                statistic.observe(viewLifecycleOwner){
                    loadingDashboardFragment.visibility = View.GONE

                    boxTotalPopulation.visibility = View.VISIBLE
                    boxNumberofAdults.visibility = View.VISIBLE
                    boxNumberofMalePopulation.visibility = View.VISIBLE
                    boxNumberofFemalePopulation.visibility = View.VISIBLE
                    boxTotalPopulationABBloodType.visibility = View.VISIBLE
                    boxTotalPopulationBBloodType.visibility = View.VISIBLE
                    boxTotalPopulationABloodType.visibility = View.VISIBLE
                    boxTotalPopulationOBloodType.visibility = View.VISIBLE

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}