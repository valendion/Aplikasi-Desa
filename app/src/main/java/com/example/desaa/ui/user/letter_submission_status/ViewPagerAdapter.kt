package com.example.desaa.ui.user.letter_submission_status

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val NUM_TABS = 5

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ItemFragment().newInstance(1)
            1 -> ItemFragment().newInstance(2)
            2 -> ItemFragment().newInstance(3)
            3 -> ItemFragment().newInstance(4)
            else -> ItemFragment().newInstance(5)
        }
    }
}