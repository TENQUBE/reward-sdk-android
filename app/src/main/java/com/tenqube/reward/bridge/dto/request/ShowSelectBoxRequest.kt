package com.tenqube.reward.bridge.dto.request

import com.tenqube.webui.dto.ShowSelectBoxDto

data class ShowSelectBoxRequest(val data: ShowSelectBoxDto) : Request {
    override fun checkParams() {
    }
}
