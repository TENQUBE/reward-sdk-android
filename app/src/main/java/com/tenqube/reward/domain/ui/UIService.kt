package com.tenqube.reward.domain.ui

import com.tenqube.reward.domain.ui.dto.*

interface UIService {
    fun finish()

    fun onClickSound()

    fun onPageLoaded()

    fun setRefreshEnabled(enabled: Boolean)

    fun showToast(msg: String)

    fun showConfirm(request: ShowConfirmDto)

    fun showSelectBox(request: ShowSelectBoxDto)

    fun openNewView(request: OpenNewViewDto)

    fun showDatePicker(request: ShowDatePickerDto)

    fun showTimePicker(request: ShowTimePickerDto)
}