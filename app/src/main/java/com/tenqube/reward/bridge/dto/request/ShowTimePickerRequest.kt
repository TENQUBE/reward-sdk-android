package com.tenqube.reward.bridge.dto.request

data class ShowTimePickerRequest(val data: ShowTimePickerDto) : Request {
    override fun checkParams() {
    }
}

data class ShowTimePickerDto(
    val date: String
)
