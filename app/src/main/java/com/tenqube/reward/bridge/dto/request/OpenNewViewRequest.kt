package com.tenqube.reward.bridge.dto.request

import com.tenqube.reward.domain.ui.dto.OpenNewViewDto

data class OpenNewViewRequest(val data: OpenNewViewDto) : Request {
    override fun checkParams() {
    }
}
