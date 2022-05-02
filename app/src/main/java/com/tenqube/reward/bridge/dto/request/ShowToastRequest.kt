package com.tenqube.reward.bridge.dto.request

data class ShowToastRequest(val data: ShowToastDto) : Request {
    override fun checkParams() {
    }
}

data class ShowToastDto(val message: String)
