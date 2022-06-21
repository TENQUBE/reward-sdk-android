package com.tenqube.reward.infrastructure.framework.widget

import android.app.Activity
import android.webkit.WebSettings
import android.webkit.WebView
import com.tenqube.reward.bridge.ui.AndroidUIBridge

class WebViewManager(private val activity: Activity) {
    private lateinit var uiService: WidgetUIService

    fun setupWebViewSettings(webView: WebView) {
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

    fun setupBridges(webView: WebView) {
        uiService = WidgetUIService(activity, webView)
        with(AndroidUIBridge(webView, uiService)) {
            webView.addJavascriptInterface(this, this.bridgeName)
        }
    }

    fun onBackPressed() {
        uiService.onBackPressed()
    }

    fun setIsPageLoaded(isPageLoaded: Boolean) {
        uiService.setIsPageLoaded(isPageLoaded)
    }
}