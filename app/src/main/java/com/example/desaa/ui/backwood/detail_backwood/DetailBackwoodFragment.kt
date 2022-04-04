package com.example.desaa.ui.backwood.detail_backwood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.desaa.databinding.FragmentDetailBackwoodBinding


class DetailBackwoodFragment : Fragment() {

    private var _binding: FragmentDetailBackwoodBinding? = null

    private val binding get() = _binding!!

    private val viewModelDetailBackwood: DetailBackwoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBackwoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        viewModelDetailBackwood = ViewModelProvider(this).get(DetailBackwoodViewModel::class.java)
        binding.apply {
            val name
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}