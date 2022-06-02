package com.example.desaa.utils

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.progressindicator.CircularProgressIndicator


class CustomWebView(val loading: CircularProgressIndicator)  : WebViewClient() {

    var ShowOrHideWebViewInitialUse = "show"


    override fun onPageStarted(webview: WebView, url: String?, favicon: Bitmap?) {

        // only make it invisible the FIRST time the app is run
        if (ShowOrHideWebViewInitialUse == "show") {
            loading.visibility = View.VISIBLE
            webview.visibility = View.INVISIBLE
        }
    }

    override fun onPageFinished(view: WebView, url: String?) {
        ShowOrHideWebViewInitialUse = "hide"
        loading.visibility = View.INVISIBLE
        view.visibility = View.VISIBLE
        super.onPageFinished(view, url)
    }
}