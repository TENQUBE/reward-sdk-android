package com.tenqube.reward.domain.ui.dto

data class ShowSelectBoxDto(
    val title: String,
    val selectedColor: String,
    val data: List<SelectBoxItemDto>
)

data class SelectBoxItemDto(
    val name: String,
    val orderByType: Int,
    val isSelected: Boolean
)
