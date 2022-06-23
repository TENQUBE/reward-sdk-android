package com.tenqube.reward.bridge.dto.request

import com.tenqube.webui.dto.ShowConfirmDto

data class ShowConfirmRequest(val data: ShowConfirmDto) : Request {
    override fun checkParams() {
    }
}


