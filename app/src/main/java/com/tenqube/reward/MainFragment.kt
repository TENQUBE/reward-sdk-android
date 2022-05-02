package com.tenqube.reward

import android.content.Context
import android.media.AudioManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import com.tenqube.reward.bridge.AndroidUI
import com.tenqube.reward.databinding.MainFragmentBinding
import java.util.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var viewDataBinding: MainFragmentBinding

    private var audioManager: AudioManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = MainFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupLifecycleOwner()
        setupAudioManager()
        setupWebView()
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupAudioManager() {
        audioManager =
            activity?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    private fun setupWebView() {
        viewModel.url.observe(this.viewLifecycleOwner) {
            viewDataBinding.webView.loadUrl(it)
        }

        with(viewDataBinding.webView) {
            setupWebViewSettings(this)
            setupBridges(this)
            setupWebViewClient(this)
            setupWebChromeClient(this)
        }
    }

    private fun setupBridges(webView: WebView) {
        with(AndroidUI(webView, viewModel)) {
            webView.addJavascriptInterface(this, this.bridgeName)
        }
    }

    private fun setupWebViewSettings(webView: WebView) {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            textZoom = 100
            cacheMode = WebSettings.LOAD_NO_CACHE
        }

        WebView.setWebContentsDebuggingEnabled(true)
    }

    private fun setupWebViewClient(webView: WebView) {
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                viewDataBinding.webView.visibility = View.GONE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
    }

    private fun setupWebChromeClient(webView: WebView) {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(cm: ConsoleMessage?): Boolean {
                val message = (cm?.message() ?: "").toLowerCase(Locale.getDefault())
                return super.onConsoleMessage(cm)
            }
        }
    }

    private fun start(url: String) {
        viewModel.start(url)
    }

    private fun reloadWebView() {
        viewDataBinding.webView.reload()
        viewDataBinding.webView.visibility = View.VISIBLE
    }

    fun finish() {
        activity?.finish()
    }

}