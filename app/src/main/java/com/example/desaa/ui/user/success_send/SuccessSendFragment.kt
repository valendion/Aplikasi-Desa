package com.example.desaa.ui.user.success_send

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.desaa.databinding.FragmentSuccessSendBinding


class SuccessSendFragment : Fragment() {

    private var _binding: FragmentSuccessSendBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        val TAG = SuccessSendFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessSendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnSeeStatus.setOnClickListener {
                val direction =
                    SuccessSendFragmentDirections.actionNavSuccessSendToNavStatus()
                Navigation.findNavController(view).navigate(direction)
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}