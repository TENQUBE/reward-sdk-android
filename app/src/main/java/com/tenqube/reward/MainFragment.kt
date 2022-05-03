package com.tenqube.reward

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tenqube.reward.bridge.AndroidUI
import com.tenqube.reward.bridge.BridgeBase
import com.tenqube.reward.databinding.MainFragmentBinding
import timber.log.Timber
import java.util.*


class MainFragment : Fragment() {

    var isPageLoaded = false

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
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewDataBinding = MainFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        viewDataBinding.search.setOnClickListener {
            Log.i("TAG","search  $it")

            viewModel.start(viewDataBinding.url.text.toString())
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnBackPressedDispatcher()
        setupLifecycleOwner()
        setupAudioManager()
        setupEvents()
        setupWebView()
        start()
    }

    private fun start() {
        activity?.intent?.getStringExtra("url")?.let {
            viewDataBinding.url.setText(it)
            viewModel.start(it)
        }
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupAudioManager() {
        audioManager =
            activity?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    var getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        Log.i("KJ", it.toString())
    }

    private fun setupEvents() {
        viewModel.url.observe(this.viewLifecycleOwner) {
            viewDataBinding.webView.loadUrl(it)
       }

        viewModel.finish.observe(this.viewLifecycleOwner) {
            finish()
        }

        viewModel.click.observe(this.viewLifecycleOwner) {
            audioManager?.playSoundEffect(AudioManager.FX_KEY_CLICK)
        }

        viewModel.onPageLoaded.observe(this.viewLifecycleOwner) {
            onPagedLoaded()
        }

        viewModel.refreshEnabled.observe(this.viewLifecycleOwner) {
        }

        viewModel.showToast.observe(this.viewLifecycleOwner) {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.showConfirm.observe(this.viewLifecycleOwner) {
            Toast.makeText(this.context, "showConfirm$it", Toast.LENGTH_SHORT).show()
        }

        viewModel.showSelectBox.observe(this.viewLifecycleOwner) {
            Toast.makeText(this.context, "showSelectBox$it", Toast.LENGTH_SHORT).show()
        }

        viewModel.openNewView.observe(this.viewLifecycleOwner) {
           when (it.type) {
                "internal" -> {
                    val intent = Intent(this.context, MainActivity::class.java)
                    intent.putExtra("url", it.url)
                    getResult.launch(intent)
                }
                else -> {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(it.url)
                    activity?.startActivity(intent)
                }
            }
        }

        viewModel.showDatePicker.observe(this.viewLifecycleOwner) {
            Toast.makeText(this.context, "showSelectBox$it", Toast.LENGTH_SHORT).show()
        }

        viewModel.showTimePicker.observe(this.viewLifecycleOwner) {
            Toast.makeText(this.context, "showTimePicker$it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupWebView() {
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

    private fun finish() {
        activity?.finish()
    }

    private fun onPagedLoaded() {
        activity?.runOnUiThread { isPageLoaded = true }
    }

    private fun setupOnBackPressedDispatcher() {
        activity?.onBackPressedDispatcher?.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(isPageLoaded) {
                    viewDataBinding.webView.loadUrl("${BridgeBase.JS}${BridgeBase.RESPONSE}onFinish()")
                } else {
                    finish()
                }
            }
        })
    }
}