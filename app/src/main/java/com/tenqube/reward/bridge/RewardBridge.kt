package com.tenqube.reward.bridge

import android.webkit.JavascriptInterface
import com.tenqube.reward.bridge.dto.request.RefreshRequest

interface RewardBridge {

    interface UI {
        val bridgeName: String

        @JavascriptInterface
        fun finish()

        @JavascriptInterface
        fun onClick()

        @JavascriptInterface
        fun onPageLoaded()

        @JavascriptInterface
        fun setRefreshEnabled(params: String?)

        @JavascriptInterface
        fun showToast(params: String?)

        @JavascriptInterface
        fun showConfirm(params: String?)

        @JavascriptInterface
        fun showSelectBox(params: String?)

        @JavascriptInterface
        fun openNewView(params: String?)

        @JavascriptInterface
        fun showDatePicker(params: String?)

        @JavascriptInterface
        fun showTimePicker(params: String?)

    }
}
