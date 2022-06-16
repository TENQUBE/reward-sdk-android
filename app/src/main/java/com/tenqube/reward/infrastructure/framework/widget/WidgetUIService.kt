package com.tenqube.reward.infrastructure.framework.widget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.webkit.WebView
import com.tenqube.reward.MainActivity
import com.tenqube.reward.bridge.BridgeBase
import com.tenqube.reward.domain.ui.UIService
import com.tenqube.reward.domain.ui.dto.*

class WidgetUIService(
    private val activity: Activity,
    private val webView: WebView
) : UIService {

    var isPageLoaded = false
    private var audioManager: AudioManager? = null

    init {
        audioManager =
            activity.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    override fun finish() {
        activity.finish()
    }

    override fun onClickSound() {
        audioManager?.playSoundEffect(AudioManager.FX_KEY_CLICK)
    }

    override fun onPageLoaded() {
        isPageLoaded = true
    }

    override fun setRefreshEnabled(enabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showToast(msg: String) {
        TODO("Not yet implemented")
    }

    override fun showConfirm(request: ShowConfirmDto) {
        TODO("Not yet implemented")
    }

    override fun showSelectBox(request: ShowSelectBoxDto) {
        TODO("Not yet implemented")
    }

    override fun openNewView(request: OpenNewViewDto) {
        when (request.type) {
            "internal" -> {
                val intent = Intent(this.activity, MainActivity::class.java)
                intent.putExtra("url", request.url)
                activity.startActivityForResult(intent, 0)
            }
            else -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(request.url)
                activity.startActivity(intent)
            }
        }
    }

    override fun showDatePicker(request: ShowDatePickerDto) {
        TODO("Not yet implemented")
    }

    override fun showTimePicker(request: ShowTimePickerDto) {
        TODO("Not yet implemented")
    }

    fun onBackPressed() {
        if(isPageLoaded) {
            webView.loadUrl("${BridgeBase.JS}${BridgeBase.RESPONSE}onFinish()")
        } else {
            finish()
        }
    }
}