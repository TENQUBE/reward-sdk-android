package com.tenqube.reward.bridge.dto.request

import com.tenqube.reward.bridge.error.ParameterError

interface Request {
    @Throws(ParameterError::class)
    fun checkParams()
}
