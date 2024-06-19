package com.example.capstoneproject.ui.explore

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivityDetailExploreBinding

class DetailExploreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the URL from the intent
        val url = intent.getStringExtra(EXTRA_URL)

        // Initialize the WebView and load the URL
        binding.webView.apply {
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                    binding.progressBar.visibility = android.view.View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.progressBar.visibility = android.view.View.GONE
                }
            }
            settings.javaScriptEnabled = true // Enable JavaScript if needed
            loadUrl(url ?: "https://example.com") // Load the URL, fallback to example.com if null
        }
    }

    companion object {
        const val EXTRA_URL = "extra_url"
    }
}
