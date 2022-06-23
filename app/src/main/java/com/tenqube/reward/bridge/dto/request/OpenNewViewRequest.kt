package com.tenqube.reward.bridge.dto.request

import com.tenqube.webui.dto.OpenNewViewDto

data class OpenNewViewRequest(val data: OpenNewViewDto) : Request {
    override fun checkParams() {
    }
}
