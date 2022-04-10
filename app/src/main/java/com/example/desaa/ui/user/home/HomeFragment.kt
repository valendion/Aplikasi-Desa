package com.example.desaa.ui.user.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desaa.databinding.FragmentHomeBinding
import kotlinx.coroutines.*
import kotlin.system.measureNanoTime

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            loadingHomeUserActivity.visibility = View.VISIBLE
            webViewHome.visibility = View.GONE

            CoroutineScope(Dispatchers.Main).launch{
                loadWebView()
                loadingHomeUserActivity.visibility = View.GONE
                webViewHome.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   private suspend fun loadWebView(){
        binding.apply {
            webViewHome.settings.javaScriptEnabled = true
            val time = measureNanoTime {
                CoroutineScope(Dispatchers.Main).launch {
                    val load = async { webViewHome.loadUrl("https://tenrigangkae.id/") }
                    load.await()
                }
            }
            delay(3000)
        }
    }
}