package com.tenqube.reward.bridge.dto

enum class StatusColor(val str: String) {
    Dark("dark"),
    White("white");

    companion object {
        fun fromStr(str: String): StatusColor? {
            return values().find { it.str == str }
        }
    }
}
