package com.tenqube.reward.bridge.dto.request

import com.tenqube.reward.domain.ui.dto.ShowDatePickerDto

data class ShowDatePickerRequest(val data: ShowDatePickerDto) : Request {
    override fun checkParams() {
    }
}
