package com.tenqube.reward.bridge.dto

import java.io.Serializable

class WebLog(
    val funcName: String,
    val request: Any? = null,
    val response: Any?
) : Serializable
