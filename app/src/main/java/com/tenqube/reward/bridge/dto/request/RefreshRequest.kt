package com.tenqube.reward.bridge.dto.request

data class RefreshRequest(val data: RefreshRequestDto) : Request {
    override fun checkParams() {
    }
}

data class RefreshRequestDto(val enabled: Boolean)
