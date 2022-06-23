package com.tenqube.reward.domain.ui.dto

import com.tenqube.webui.dto.ButtonDetailDto
import com.tenqube.webui.dto.ButtonDto

data class ShowConfirmDto(
    val title: String,
    val message: String,
    val positive: ButtonDto,
    val negative: ButtonDto
)

data class ButtonDto(
    val button: ButtonDetailDto
)

data class ButtonDetailDto(
    val text: String,
    val color: String,
    val bgColor: String
)