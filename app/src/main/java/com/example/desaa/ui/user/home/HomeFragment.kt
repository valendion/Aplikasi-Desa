package com.example.desaa.ui.user.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desaa.databinding.FragmentHomeBinding
import com.example.desaa.utils.CustomWebView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var customWebView: CustomWebView

    companion object {
        val TAG = HomeFragment::class.java.simpleName
    }

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

        try {

            binding.apply {

                customWebView = CustomWebView(loadingHomeUserActivity)
                webViewHome.webViewClient = customWebView

                webViewHome.settings.javaScriptEnabled = true
                webViewHome.settings.domStorageEnabled = true
                webViewHome.loadUrl("https://tenrigangkae.id/");

                swipeHomeUser.setOnRefreshListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(2000)
                        swipeHomeUser.isRefreshing = false
                        webViewHome.loadUrl("https://tenrigangkae.id/");
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