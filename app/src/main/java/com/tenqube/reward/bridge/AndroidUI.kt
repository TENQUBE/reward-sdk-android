package com.tenqube.reward.bridge

import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.tenqube.reward.MainViewModel
import com.tenqube.reward.bridge.dto.request.*

class AndroidUI(
    webView: WebView,
    private val viewModel: MainViewModel
) : BridgeBase(webView), RewardBridge.UI {

    override val bridgeName: String
        get() = "visualUI"


    @JavascriptInterface
    override fun finish() {
        execute(
            funcName = this@AndroidUI::finish.name,
            params = null,
            classOfT = Any::class.java,
            body = {
            viewModel.finish()
        })
    }


    @JavascriptInterface
    override fun onClick() {
        execute(funcName = this@AndroidUI::onClick.name,
            params = null,
            classOfT = Any::class.java,
            body = {
                it?.let {
                    viewModel.onClickSound()
                }
            })
    }

    @JavascriptInterface
    override fun onPageLoaded() {
        execute(funcName = this@AndroidUI::onPageLoaded.name,
            params = null,
            classOfT = Any::class.java,
            body = {
                it?.let {
                    viewModel.onPageLoaded() // 백키 권한을 웹뷰로 전달
                }
            })
    }

    @JavascriptInterface
    override fun setRefreshEnabled(params: String?) {
        execute(funcName = this@AndroidUI::setRefreshEnabled.name,
            params = params,
            classOfT = RefreshRequest::class.java,
            body = {
                it?.let {
                    viewModel.setRefreshEnabled(it.data.enabled)
                }
            })
    }

    @JavascriptInterface
    override fun showToast(params: String?) {
        execute(funcName = this@AndroidUI::showToast.name,
            params = params,
            classOfT = ShowToastRequest::class.java,
            body = {
                it?.let {
                    viewModel.showToast(it.data.message)
                }
            })
    }

    @JavascriptInterface
    override fun showConfirm(params: String?) {
        execute(funcName = this@AndroidUI::showConfirm.name,
            params = params,
            classOfT = ShowConfirmRequest::class.java,
            body = {
                it?.let {
                    viewModel.showConfirm(it.data)
                }
            })
    }

    @JavascriptInterface
    override fun showSelectBox(params: String?) {
        execute(funcName = this@AndroidUI::showSelectBox.name,
            params = params,
            classOfT = ShowSelectBoxRequest::class.java,
            body = {
                it?.let {
                    viewModel.showSelectBox(it.data)
                }
            })
    }

    @JavascriptInterface
    override fun openNewView(params: String?) {
        execute(funcName = this@AndroidUI::openNewView.name,
            params = params,
            classOfT = OpenNewViewRequest::class.java,
            body = {
                it?.let {
                    viewModel.openNewView(it.data)
                }
            })
    }

    @JavascriptInterface
    override fun showDatePicker(params: String?) {
        execute(funcName = this@AndroidUI::showDatePicker.name,
            params = params,
            classOfT = ShowDatePickerRequest::class.java,
            body = {
                it?.let {
                    viewModel.showDatePicker(it.data)
                }
            })
    }

    @JavascriptInterface
    override fun showTimePicker(params: String?) {
        execute(funcName = this@AndroidUI::showTimePicker.name,
            params = params,
            classOfT = ShowTimePickerRequest::class.java,
            body = {
                it?.let {
                    viewModel.showTimePicker(it.data)
                }
            })
    }
}
