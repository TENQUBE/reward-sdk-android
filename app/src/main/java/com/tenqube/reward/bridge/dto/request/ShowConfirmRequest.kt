package com.tenqube.reward.bridge.dto.request

import com.tenqube.reward.domain.ui.dto.ShowConfirmDto

data class ShowConfirmRequest(val data: ShowConfirmDto) : Request {
    override fun checkParams() {
    }
}


