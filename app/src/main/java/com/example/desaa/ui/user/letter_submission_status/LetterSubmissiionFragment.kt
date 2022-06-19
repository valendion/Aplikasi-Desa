package com.example.desaa.ui.user.letter_submission_status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.desaa.databinding.FragmentLetterSubmissiionBinding
import com.example.desaa.utils.Constant
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LetterSubmissiionFragment : Fragment() {
    private var _binding: FragmentLetterSubmissiionBinding? = null

    private val binding get() = _binding!!

    private val viewModelLetterSubmissionStatus: LetterSubmissionStatusViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterSubmissiionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewModelLetterSubmissionStatus.apply {
                isLoading.observe(viewLifecycleOwner){ setLoading(it) }

                getDataSubmissionStatus()

                val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)
                viewPager2.adapter = adapter

                TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                    tab.text = Constant.NAME_TABS[position]
                }.attach()
            }

            swipeSubmissionLetterUser.setOnRefreshListener {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(2000)
                    swipeSubmissionLetterUser.isRefreshing = false

                    viewModelLetterSubmissionStatus.getDataSubmissionStatus()
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLoading(status: Boolean) {
        if (status) binding.loadingLoadingLetterSubmissionFragment.visibility = View.VISIBLE
        else binding.loadingLoadingLetterSubmissionFragment.visibility = View.GONE

    }
}