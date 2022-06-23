package com.tenqube.reward.bridge.dto.request

import com.tenqube.webui.dto.ShowTimePickerDto

data class ShowTimePickerRequest(val data: ShowTimePickerDto) : Request {
    override fun checkParams() {
    }
}